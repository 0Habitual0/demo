package com.habitual.demo.common.utils;

import com.habitual.demo.common.exception.TokenValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${system.custom.jwtSecretKey}")
    private String secretKey;

    @Getter
    private Key signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 生成不带过期时间的 JWT 令牌
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        // 将令牌存储在 Redis 中，并设置过期时间
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(username, token, 10, TimeUnit.MINUTES); // 10分钟有效期
        return token;
    }

    // 验证令牌，并在验证成功时续期，返回令牌用户名
    public String validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        String username = validateJwtToken(token);
        if (!username.isEmpty() && validateRedisToken(username, token)) {
            return username;
        }
        return null;
    }

    // Jwt判断逻辑
    public String validateJwtToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            // 处理token过期异常
            throw new TokenValidationException("Token has expired", 50014);
        } catch (SignatureException e) {
            // 处理token签名异常
            throw new TokenValidationException("Invalid token signature", 50008);
        } catch (Exception e) {
            // 处理其他非法token异常
            throw new TokenValidationException("Illegal token", 50008);
        }
    }

    // Redis判断逻辑
    public Boolean validateRedisToken(String username, String token) {
        // 使用MGET和getExpire组合命令，减少一次性获取多个键的值
        List<Object> results = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.keyCommands().ttl(username.getBytes()); // 获取剩余存活时间
            connection.stringCommands().get(username.getBytes()); // 获取存储的token
            return null;
        });

        // 解析结果
        Long expireTime = (Long) results.get(0);
        String storedToken = (String) results.get(1);

        // 检查token的剩余存活时间
        if (expireTime == null || expireTime == -2) {
            throw new TokenValidationException("Invalid token", 50014); // 失效的token
        }
        if (!token.equals(storedToken)) {
            throw new TokenValidationException("Illegal token", 50008); // 非法token
        }

        // token验证通过且未过期 续期：延长令牌在 Redis 中的过期时间
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(username, token, 10, TimeUnit.MINUTES); // 重新设置 10分钟有效期
        return true;
    }

    // 注销令牌
    public void deleteToken() {
        // 从 Redis 中删除令牌
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            redisTemplate.delete(authentication.getName());
        }
    }

}

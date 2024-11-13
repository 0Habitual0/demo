package com.habitual.demo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${system.custom.jwtSecretKey}")
    private String SECRET_KEY;

    // 生成不带过期时间的 JWT 令牌
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        // 将令牌存储在 Redis 中，并设置过期时间
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(username, token, 10, TimeUnit.MINUTES); // 10分钟有效期
        return token;
    }

    // 验证令牌，并在验证成功时续期
    public String validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String storedToken = ops.get(username);

        if (token.equals(storedToken)) {
            // 续期：延长令牌在 Redis 中的过期时间
            ops.set(username, token, 10, TimeUnit.MINUTES); // 重新设置 10分钟有效期
            return username;
        }
        return null;
    }

    private String validateTokenFake(String token) {
        return "fakeUser";
    }

}


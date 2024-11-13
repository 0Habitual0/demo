package com.habitual.demo.common.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Token身份验证过滤器类
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Token");

        if (validateToken(token)) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(String token) {
        // 令牌验证逻辑，如签名验证、过期时间检查等
        return !StringUtils.isBlank(token);
    }

    private Authentication getAuthentication(String token) {
        // 这是一个硬编码的示例，实际应用中应解析令牌并验证其有效性
        // 假设令牌有效并包含用户信息：用户名 "user" 和角色 "ROLE_USER"

        String username = "user1"; // 从令牌中解析出的用户名
        String role = "admin"; // 从令牌中解析出的角色

        // 创建简单的认证对象，包含用户名和角色信息
        return new UsernamePasswordAuthenticationToken(
                username,
                null, // 这里可以放置密码，如果需要的话
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}

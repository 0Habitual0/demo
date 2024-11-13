package com.habitual.demo.common.security;

import com.habitual.demo.common.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Token");
        String username = jwtTokenUtil.validateToken(token);
        if (username != null) {
            Authentication authentication = getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String username) {
        // 创建简单的认证对象，包含用户名和角色信息
        return new UsernamePasswordAuthenticationToken(
                username,
                null, // 这里可以放置密码，如果需要的话
                Collections.emptyList() // 不使用权限
//                Collections.singletonList(new SimpleGrantedAuthority("user"))
        );
    }

}

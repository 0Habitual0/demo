package com.habitual.demo.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.exception.TokenValidationException;
import com.habitual.demo.common.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * Token身份验证过滤器类
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private SessionRegistry sessionRegistry;

    @Autowired
    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-Token");

        try {
            String username = jwtTokenUtil.validateToken(token);
            if (username != null) {
                // 检查是否有其他活跃的会话
                if (isUserAlreadyLoggedIn(username)) {
                    throw new TokenValidationException("当前用户在线，无法登录", 50012);
                }
                Authentication authentication = getAuthentication(username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (TokenValidationException ex) {
            handleTokenValidationException(response, ex);
            return;
        }
        filterChain.doFilter(request, response);
    }

    // 检查用户是否已经登录
    private boolean isUserAlreadyLoggedIn(String username) {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object principal : allPrincipals) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Authentication getAuthentication(String username) {
        // 创建简单的认证对象，包含用户名和角色信息
        return new UsernamePasswordAuthenticationToken(
                username,
                null, // 这里可以放置密码，如果需要的话
                Collections.emptyList() // 不使用权限
        );
    }

    // 处理异常，返回自定义信息
    private void handleTokenValidationException(HttpServletResponse response, TokenValidationException ex) throws IOException {
        // 设置响应状态码
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应内容类型
        response.setContentType("application/json");
        // 将自定义响应对象转换为 JSON 并写入响应
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(CommonResponse.fail(ex.getErrorCode(), ex.getMessage())));
        writer.flush();
        writer.close();
    }

}

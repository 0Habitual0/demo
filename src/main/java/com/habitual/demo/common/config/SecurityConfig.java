package com.habitual.demo.common.config;

import com.habitual.demo.common.security.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable) // 禁用 CORS防护（跨域资源共享）
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF防护（跨站请求伪造）
                .authorizeHttpRequests(authorize -> authorize // HTTP 请求授权
                        .requestMatchers(HttpMethod.POST, "/user/login")
                        .permitAll() // 允许路径的请求不进行身份验证
                        .anyRequest().authenticated() // 其他所有请求都需要身份验证
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1) // 设置同一用户最多允许的会话数
                        .maxSessionsPreventsLogin(true) // false表示后登录的用户会顶掉先登录的用户
                        .sessionRegistry(sessionRegistry())
                )
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

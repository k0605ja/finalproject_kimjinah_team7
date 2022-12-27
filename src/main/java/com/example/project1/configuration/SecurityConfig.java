package com.example.project1.configuration;

import com.example.project1.service.UserService;
import com.example.project1.token.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Spring container에 관리되는 config 설정
@EnableWebSecurity // 스프링 시큐리티 사용을 위한 어노테이션 선언
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Value("${jwt.token.secret}") // .yml
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception {
        return httpSecurity
                .httpBasic().disable() // JWT는 httpBearer 방식이므로 httpBasic 방식 블록
                .csrf().disable() // 사이트 간 요청 위조 방지
                .cors().and() // CorsFilter 사용 (도메인이 다를 시 허용)
                .authorizeRequests() // 보호된 리소스 URI에 접근할 수 있는 권한 설정
                .antMatchers("/api/v1/users/join", "/api/v1/users/login", "/swagger-ui").permitAll() // 모든 get 요청 허용
                .antMatchers("/api/v1/**").authenticated() // 권한이 없으면 접근 블록
                .and()
                // 세션 사용 안함(JWT사용)
                .sessionManagement() // 세션 관리 구성
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// JWT 사용 시, 세션 필요 없으므로 STATELESS 처리
                .and()
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class) //UserNamePasswordAuthenticationFilter 적용 전, JWTTokenFilter 적용 및 인증
                .build();
    }

}
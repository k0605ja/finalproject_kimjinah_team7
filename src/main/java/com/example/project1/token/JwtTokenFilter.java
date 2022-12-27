package com.example.project1.token;

import com.example.project1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 허가가 header가 없거나 Bearer로 시작하지 않을 시, header에서 token 리턴
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader:{}", authorizationHeader);

        // token이 없거나 부적절한 토큰일 때
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("Token이 없거나 잘못 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // header에서 token 분리
        String token;

        try {
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            log.error("Token 추출에 실패 했습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        // Token 만료 되었는지 확인
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }


        // Token Claim에서 UserName 추출
        String userName = JwtTokenUtil.getUserName(token, secretKey);
        log.info("userName:{}", userName);

        // 권한 부여
        // 요청(아이디, 패스워드) 인증
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("", null, List.of(new SimpleGrantedAuthority("USER")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // 컨텍스트에 인증을 설정 후, 현재 사용자가 인증 되도록 저장
        filterChain.doFilter(request, response);

    }
}

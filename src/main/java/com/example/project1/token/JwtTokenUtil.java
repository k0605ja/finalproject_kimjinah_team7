package com.example.project1.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {


    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }


    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody()
                .get("userName", String.class);
    }


    public static String createToken(String userName, String key, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 현재 시간으로 토큰 발행 생성일 설정
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 현재 시간으로 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key) // 받은 key로 잠금
                .compact();
    }

}

package com.example.project1.service;

import com.example.project1.configuration.EncrypterConfig;
import com.example.project1.domain.dto.user.UserJoinRequest;
import com.example.project1.domain.entity.User;
import com.example.project1.exception.AppException;
import com.example.project1.exception.ErrorCode;
import com.example.project1.repository.UserRepository;
import com.example.project1.token.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncrypterConfig encoder;


    // Token 유효 시간 설정
    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expiredTimeMs = 1000 * 60 * 60; // Token 유효 시간: 1시간


    // 회원 가입
    public User join(UserJoinRequest userJoinRequest) {

        // id 중복 검사
        User userList = userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s은 이미 가입된 이름 입니다.", userJoinRequest.getUserName()));
                });

        // 회원 저장
        User savedUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));

        // 저장 후, UserDto로 데이터 전달
       return User.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
              .build();
    }

    // 로그인
    public String login(String userName, String password) {

        // userName(id) 유무 확인
        User user = (User userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        // password 일치 확인
        if (!encoder.matches(password, user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        // 예외 없으면, Token 발행
        return JwtTokenUtil.createToken(userName, secretKey, expiredTimeMs);
    }

}

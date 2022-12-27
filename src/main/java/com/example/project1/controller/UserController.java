package com.example.project1.controller;

import com.example.project1.domain.dto.Response;
import com.example.project1.domain.dto.user.*;
import com.example.project1.domain.entity.User;
import com.example.project1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor // 필요한 생성자 자동 생성
@Slf4j
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) { // Json 형태로 UserJoinRequest에 값을 받음
        User user = userService.join(userJoinRequest);
        log.info("회원가입 성공");
        return Response.success(new UserJoinResponse(user.getId(), user.getUserName()));
    }


    // 로그인
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {

        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());

        // 로그인 성공 하면, 토큰 리턴
        return Response.success(new UserLoginResponse(token));
    }


}
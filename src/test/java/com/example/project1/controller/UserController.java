package com.example.project1.controller;

import com.example.project1.domain.dto.user.User;
import com.example.project1.domain.dto.user.UserJoinRequest;
import com.example.project1.exception.AppException;
import com.example.project1.exception.ErrorCode;
import com.example.project1.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerTest.class)
class UserControllerTest {
    @Autowired // 필요한 의존 객체의 타입에 해당하는 빈을 찾아 주입
    MockMvc mockMvc; // 테스트에 필요한 기능만 가지는 가짜 객체를 만들어 애플리케이션 서버에 배포하지 않고 스프링 MVC 동작을 재현할 수 있는 클래스

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper; // JSON을 객체로 변환


    // 회원가입
    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("kimjinah")
                .password("0000")
                .email("k0605ja2@naver.com")
                .build();

        // when
        when(userService.join(any())).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")"/api/v1/users/join")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 - id 중복")
    void join_failed() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("kimjinah")
                .password("0000")
                .email("k0605ja2@naver.com")
                .build();

        when(userService.join(any())).thenThrow(new AppException(ErrorCode.DUPLICATED_USER_NAME));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }


    // 로그인
    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    public void login_success() throws Exception {
        // given
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .userName("jinah")
                .password("1234")
                .build();

        // when
        when(userService.login(any(), any())).thenReturn("token");

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userLoginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").exists())
                .andExpect(jsonPath("$.result.jwt").exists());

        // then
        verify(userService).login(any(), any());
    }

    @Test
    @DisplayName("로그인 실패 - id 없음")
    @WithMockUser
    public void login_fail_empty_userName() throws Exception {
        // given
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .userName("jinah")
                .password("1234")
                .build();


        // when
        when(userService.login(any(), any())).thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userLoginRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());

        // then
        verify(userService).login(any(), any());
    }

    @Test
    @DisplayName("로그인 실패 - password 잘못 입력함")
    @WithMockUser
    public void login_fail_password_wrong() throws Exception {
        // given

        // when
        when(userService.login(any(), any())).thenThrow(new AppException(ErrorCode.INVALID_PASSWORD));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userLoginRequest)))
                .andDo(print())
                .andExpect(status().isUnauthorized());

        // then
        verify(userService).login(any(), any());
    }


}
package com.example.project1.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse<T> { // 응답을 받을 땐 password를 숨겨야 하기 때문에 필드에 선언하지 않음
    private Long userId;
    private String userName;


}

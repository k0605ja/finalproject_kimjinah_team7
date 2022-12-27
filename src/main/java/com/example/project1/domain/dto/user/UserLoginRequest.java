package com.example.project1.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserLoginRequest {

    private String userName;
    private String password;

}

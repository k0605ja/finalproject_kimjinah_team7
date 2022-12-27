package com.example.project1.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserLoginResponse {
    private  String jwt;

}

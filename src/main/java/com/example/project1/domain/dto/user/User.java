package com.example.project1.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User { // Dto: Service → Dto → Response<T>로, service에서 받은 값을 Response로 전달

    private Long id;
    private String userName;
    private String password;

}

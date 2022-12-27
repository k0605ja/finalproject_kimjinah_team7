package com.example.project1.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJoinRequest { // Controller 에서 Json 형태로 필드 값을 받음
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password){ // UserJoinRequest의 값을 User로 전달
        return User.builder()
                .userName(this.userName)
                .password(this.password)
                .build();
}

//    public UserDto join(UserJoinRequest request) {
//        userRepository.findByUserName(request.getUserName())
//                .ifPresent(user ->{
//                    throw new AppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName:%s", request.getUserName()));
//                });
//        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
//        return UserDto.builder()
//                .id(savedUser.getId()
//                        .userName(savedUser.getUserName())
//                        .email(savedUser.getEmailAddress())
//                        .build());
//    }
}
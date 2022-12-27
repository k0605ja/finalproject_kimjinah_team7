package com.example.project1.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    // 회원가입 시, userName의 중복 체크
    // 로그인 시, 입력한 userName을 DB에서 검색 후, Optional<User> 형태로 리턴

    Optional<User> findOptionalByUserName(String username);

}


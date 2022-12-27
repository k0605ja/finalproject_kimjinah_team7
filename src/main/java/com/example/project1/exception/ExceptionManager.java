package com.example.project1.exception;

import com.example.project1.domain.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 전역적 예외 처리
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage()));

    }

    // 컨트롤러 예외 발생 시 실행
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> hospitalReviewAppExceptionHandler(AppException e) {
        // <?> 는 특정 클래스를 상속 받은 클래스만 들어올 수 있도록 제한 되어 있다.
        // <?>: AppException은 RuntimeException을 상속 받음

        // Json 형식으로 리턴
        return ResponseEntity.status(e.getErrorCode().getStatus()) // CONFLICT : 409
                .body(Response.error(e.getErrorCode().getMessage())); // Response.error(User name is duplicated.)



    }
}
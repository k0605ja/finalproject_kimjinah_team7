package com.example.project1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080/api/v1/hello

    @RestController
    public class HelloController {

        @GetMapping("/api/v1/hello")
        public ResponseEntity<String> hello() {
            return ResponseEntity.ok().body("hello");
        }
    }



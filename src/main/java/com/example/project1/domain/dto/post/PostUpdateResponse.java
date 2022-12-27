package com.example.project1.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PostUpdateResponse {

    private Long postId;
    private String message;
}
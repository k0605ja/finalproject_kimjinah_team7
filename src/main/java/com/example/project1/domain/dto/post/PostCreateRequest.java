package com.example.project1.domain.dto.post;

import com.example.project1.domain.dto.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostCreateRequest {

    private String title;
    private String body;

    public static Post toEntity(PostCreateRequest postCreateRequest) {
        return Post.builder()
                .title(postCreateRequest.getTitle())
                .body(postCreateRequest.getBody())
                .build();
    }
}
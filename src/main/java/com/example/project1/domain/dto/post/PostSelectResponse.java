package com.example.project1.domain.dto.post;
import com.example.project1.domain.dto.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class PostSelectResponse {

    private Integer id;
    private String title;
    private String body;
    private String userName;
    private LocalDateTime createdAt;

    public static PostSelectResponse of(Post post) {
        return PostSelectResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUser().getUserName())
                .createdAt(post.getRegisteredAt())
                .build();
    }

    public static Object error(String message) {
    }
}


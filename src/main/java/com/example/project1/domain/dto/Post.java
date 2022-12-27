package com.example.project1.domain.dto;
import com.example.project1.domain.dto.user.User;
import lombok.*;
import javax.persistence.*;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Post extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String body;

    @JoinColumn(name = "user_id") // 연관관계의 주인
    private User user;

    @Builder
    public Post(Long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }


}

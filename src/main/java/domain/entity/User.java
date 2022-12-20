package domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;
    private String phone;
    private String email;

    public User(String userId, String password, String userName, String phone, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public User(UserJoinRequest userJoinRequest) {
        this.userId = userJoinRequest.getUserName();
        this.password = userJoinRequest.getPassword();
        this.userName = userJoinRequest.getUserName();
        this.phone = userJoinRequest.getPhone();
        this.email = userJoinRequest.getEmail();
    }
}

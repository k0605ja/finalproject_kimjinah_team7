package User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    //DB에 unique를 걸어준다.
    private String userName;
    private String password;
    private String emailAddress;
}
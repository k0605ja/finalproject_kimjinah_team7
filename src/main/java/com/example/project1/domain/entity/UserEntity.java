//package domain.entity;
//
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
//public class UserEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Interger id;
//
//    @Column(nullable = false, unique = true)
//    private Timestamp deletedAt;
//    private String password;
//    private Timestamp registeredAt;
//    private UserRole role;
//    private Timestamp updatedAt;
//    private String userName;
//
//}

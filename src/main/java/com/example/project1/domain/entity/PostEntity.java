//package domain.entity;
//
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
//public class PostEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(nullable = false, unique = true)
//    private String body;
//    private Timestamp deletedAt;
//    private Timestamp registeredAt;
//    private String title;
//    private Timestamp updatedAt;
//
//
//}

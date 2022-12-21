//package domain.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
//@AllArgsConstructor
//@Getter
//@Entity
//public class CommentEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String comment;
//    private Timestamp deletedAt;
//    private Timestamp registeredAt;
//    private Timestamp updatedAt;
//}

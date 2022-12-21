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
//public class AlarmEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Interger id;
//
//    @Column(nullable = false, unique = true)
//    private String alarmType;
//    private AlarmArgs args;
//    private AlarmArgs deletedAt;
//    private Timestamp deletedAt;
//    private Timestamp registeredAt;
//    private Timestamp updatedAt;
//
//
//
//
//}

package com.example.project1.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@ToString
@NoArgsConstructor
/*@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP where id = ?")*/
public class User{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String password;


}
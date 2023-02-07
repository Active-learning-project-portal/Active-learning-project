package com.example.Active.Learning.project.account.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private Long id;
    private String username;
    private  String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}

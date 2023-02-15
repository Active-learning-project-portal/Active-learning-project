package com.example.Active.Learning.project.account.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
        })
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String provider;

    private String firstname;
    private String lastname;
    private String avatar;
    private String authType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username,String firstname,String lastname,String authType, String encode,String provider,String avatar) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.authType = authType;
        this.username = username;
        this.password = encode;
        this.provider = provider;
        this.avatar = avatar;
    }
}


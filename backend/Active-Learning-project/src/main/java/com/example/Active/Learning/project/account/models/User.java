package com.example.Active.Learning.project.account.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    private Date joined= new Date();
    private Date lastSeen;
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "users_course",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Course course;

    public User(String username,
                String firstname,
                String lastname,
                String authType,
                String encode,
                String provider,
                String avatar,
                Date lastSeen,
                Boolean isActive) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.authType = authType;
        this.username = username;
        this.password = encode;
        this.provider = provider;
        this.avatar = avatar;
        this.lastSeen = lastSeen;
        this.isActive = isActive;
    }
}


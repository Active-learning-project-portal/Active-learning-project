package com.example.Active.Learning.project.account.models.users;

import com.example.Active.Learning.project.account.models.role.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    private String username;
    private String password;
    private String provider;
    private String firstname;
    private String lastname;
    private String avatar;
    private Date dateJoined;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username,
                String firstname,
                String lastname,
                String encode,
                String provider,
                String avatar,
                Date dateJoined) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = encode;
        this.provider = provider;
        this.avatar = avatar;
        this.dateJoined = dateJoined;
    }
}

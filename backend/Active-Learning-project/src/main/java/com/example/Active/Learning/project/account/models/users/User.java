package com.example.Active.Learning.project.account.models.users;

import com.example.Active.Learning.project.account.models.BaseEntity;
import com.example.Active.Learning.project.account.models.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
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
@AllArgsConstructor
public class User extends BaseEntity {
    private String username;
    private String password;
    private String provider;
    private String firstname;
    private String lastname;
    private String avatar;
    private Date dateJoined;

    @Transient
    private String token;

    @Transient
    private String tokenType;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;

    public User(String username,
                String firstname,
                String lastname,
                String encode,
                String provider,
                String avatar,
                Date dateJoined
                ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = encode;
        this.provider = provider;
        this.avatar = avatar;
        this.dateJoined = dateJoined;
    }
}

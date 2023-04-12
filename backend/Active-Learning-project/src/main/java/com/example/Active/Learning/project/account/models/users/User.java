package com.example.Active.Learning.project.account.models.users;

import com.example.Active.Learning.project.base.models.BaseEntity;
import com.example.Active.Learning.project.account.models.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import javax.xml.transform.Source;
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
@AllArgsConstructor
@ToString
@Builder
public class User extends BaseEntity {

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String username;

    private String password;
    private String provider;
    private String firstname;
    private String lastname;
    private String avatar;
    @Transient
    private String token;
    @Transient
    private String tokenType;
    private Set<ERole> roles;
}

package com.example.Active.Learning.project.account.models.role;


import com.example.Active.Learning.project.account.models.BaseEntity;
import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor

public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users;

    public Role(ERole name) {
        this.name = name;
    }
}

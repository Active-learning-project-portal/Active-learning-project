package com.example.Active.Learning.project.account.models.users;

import com.example.Active.Learning.project.account.models.enums.ELoginType;
import com.example.Active.Learning.project.account.models.enums.EUserState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users_log")
@NoArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    @NonNull
    private Date lastSeen;
    @NonNull
    private ELoginType eLoginType;

    @NonNull
    private EUserState eUserState;

    @NonNull
    private UUID userId;
}

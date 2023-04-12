package com.example.Active.Learning.project.account.models.users;

import com.example.Active.Learning.project.base.models.BaseEntity;
import com.example.Active.Learning.project.account.models.enums.ELoginType;
import com.example.Active.Learning.project.account.models.enums.EUserState;
import jakarta.persistence.*;
import lombok.*;

//TODO please check the sessions factory or alternatives to deal with this.
@Setter
@Getter
@Entity
@Table(name = "user_log")
@NoArgsConstructor
@AllArgsConstructor
public class UserLog extends BaseEntity {
    @NonNull
    private ELoginType loginType;
    @NonNull
    private EUserState userState;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;
}

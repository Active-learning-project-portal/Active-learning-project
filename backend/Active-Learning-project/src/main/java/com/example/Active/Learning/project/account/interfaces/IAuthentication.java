package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthentication {
    User authenticate(@RequestBody UserRequest userRequest);
}

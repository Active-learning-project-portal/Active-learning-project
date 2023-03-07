package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.payload.request.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthentication {
    ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest);
}

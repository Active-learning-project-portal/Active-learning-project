package com.example.Active.Learning.project.authenticate.interfaces;

import com.example.Active.Learning.project.authenticate.payload.request.AuthRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthentication {
    ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest);
}

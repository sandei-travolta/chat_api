package com.kafkadev.chat_api.controllers;

import com.kafkadev.chat_api.dtos.AuthResponse;
import com.kafkadev.chat_api.dtos.LoginRequest;
import com.kafkadev.chat_api.dtos.RegisterRequest;
import com.kafkadev.chat_api.dtos.RegisterResponse;
import com.kafkadev.chat_api.services.userService.AuthService;
import com.kafkadev.chat_api.services.userService.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentiationController {
    private final UserServices userServices;
    private  final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request){
            if(userServices.registerUser(request)){
                RegisterResponse registerResponse=new RegisterResponse();
                registerResponse.setMessage("user Created Successfuly");
                registerResponse.setTimeStamp(LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
            }
            RegisterResponse registerResponse=new RegisterResponse();
            registerResponse.setMessage("Error Creating User");
            registerResponse.setTimeStamp(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(registerResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

package com.kafkadev.chat_api.controllers;

import com.kafkadev.chat_api.dtos.AuthResponse;
import com.kafkadev.chat_api.dtos.LoginRequest;
import com.kafkadev.chat_api.entities.UserEntity;
import com.kafkadev.chat_api.services.userService.AuthService;
import com.kafkadev.chat_api.services.userService.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentiationController {
    private final UserServices userServices;
    private  final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userBody){
        UserEntity user=userServices.registerUser(userBody);
            return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}

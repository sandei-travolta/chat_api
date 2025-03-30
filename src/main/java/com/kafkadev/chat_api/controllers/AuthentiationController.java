package com.kafkadev.chat_api.controllers;

import com.kafkadev.chat_api.entities.UserEntity;
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
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userBody){
        UserEntity user=userServices.registerUser(userBody);
            return ResponseEntity.ok(user);
    }
}

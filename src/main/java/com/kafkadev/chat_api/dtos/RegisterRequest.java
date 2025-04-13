package com.kafkadev.chat_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String userName;
    private String password;
    private String name;
    private String mobileNo;
    private String email;
}

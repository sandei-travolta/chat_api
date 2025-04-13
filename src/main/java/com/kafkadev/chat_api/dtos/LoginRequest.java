package com.kafkadev.chat_api.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

}

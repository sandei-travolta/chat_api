package com.kafkadev.chat_api.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {
    private String email;
    private String password;

}

package com.kafkadev.chat_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String token;

    public static AuthResponse of(String token, String username) {
        return new AuthResponse(token, username);
    }
}
package com.kafkadev.chat_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private LocalDateTime timeStamp;
    private int statusCode;
}

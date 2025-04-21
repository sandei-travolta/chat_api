package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.dtos.RegisterRequest;
import com.kafkadev.chat_api.entities.UserEntity;

public interface UserServiceInteface {
    boolean registerUser(RegisterRequest registerRequest);

}

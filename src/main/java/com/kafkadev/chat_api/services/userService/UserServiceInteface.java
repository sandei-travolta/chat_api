package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.entities.UserEntity;

public interface UserServiceInteface {
    UserEntity registerUser(UserEntity user);

}

package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.entities.UserEntity;
import com.kafkadev.chat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserServiceInteface{
    @Autowired
    public UserRepository userRepository;
    @Override
    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }
}

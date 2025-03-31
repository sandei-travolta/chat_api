package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.entities.Role;
import com.kafkadev.chat_api.entities.UserEntity;
import com.kafkadev.chat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDateTime;

@Service
public class UserServices implements UserServiceInteface{
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Override
    public UserEntity registerUser(UserEntity user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

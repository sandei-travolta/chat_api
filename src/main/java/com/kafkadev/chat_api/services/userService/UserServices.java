package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.dtos.RegisterRequest;
import com.kafkadev.chat_api.entities.AuthEntity;
import com.kafkadev.chat_api.entities.UserEntity;
import com.kafkadev.chat_api.repository.AuthRepository;
import com.kafkadev.chat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServices implements UserServiceInteface{
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public AuthRepository authRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Override
    public boolean registerUser(RegisterRequest registerRequest) {
        UserEntity user=new UserEntity();
        user.setCreatedAt(LocalDateTime.now());
        user.setUserName(registerRequest.getUserName());
        user.setMobileNo(registerRequest.getMobileNo());
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        UserEntity savedUser=userRepository.save(user);
        if(savedUser!=null){
            AuthEntity authEntity=new AuthEntity();
            authEntity.setUserName(registerRequest.getEmail());
            authEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            authEntity.setUid(savedUser.getUserId());
            AuthEntity savedAuth=authRepository.save(authEntity);
            if (savedAuth!=null){
                return true;
            }
        }
        return false;
    }
}

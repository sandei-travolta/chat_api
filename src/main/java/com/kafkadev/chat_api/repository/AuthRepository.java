package com.kafkadev.chat_api.repository;

import com.kafkadev.chat_api.entities.AuthEntity;
import com.kafkadev.chat_api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity,Long> {
    Optional<AuthEntity> findByEmail(String email);
}

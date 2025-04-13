package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.dtos.AuthResponse;
import com.kafkadev.chat_api.dtos.LoginRequest;
import com.kafkadev.chat_api.repository.AuthRepository;
import com.kafkadev.chat_api.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse authenticate(LoginRequest request) {
        try {
            // Authenticate user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Get user details and generate token
            var user =authRepository.findByEmail(request.getEmail())
                    .orElseThrow(()->new RuntimeException("User not found"));
            var jwtToken = jwtService.generateToken((UserDetails) user);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .username(user.getUserName())
                    .build();

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email/password");
        }
    }
}
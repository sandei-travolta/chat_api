package com.kafkadev.chat_api.services.userService;

import com.kafkadev.chat_api.dtos.AuthResponse;
import com.kafkadev.chat_api.dtos.LoginRequest;
import com.kafkadev.chat_api.repository.UserRepository;
import com.kafkadev.chat_api.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse authenticate(LoginRequest request) {
        System.out.println(request);
        try {
            // Authenticate user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // Get user details and generate token
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            var jwtToken = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .username(user.getUsername())
                    .build();

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email/password");
        }
    }
}
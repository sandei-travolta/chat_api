package com.kafkadev.chat_api.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {
    private final Dotenv dotenv = Dotenv.load();
    public String getSecretKey() {
        return dotenv.get("SECRET_KEY");
    }
}

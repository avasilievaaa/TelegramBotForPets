package com.course7.telegrambotforpets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramBotForPetsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelegramBotForPetsApplication.class, args);
    }

    public TelegramBotForPetsApplication() {
    }
}
package com.solomon.todo.intergration.telegram;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class TelegramBeans {

    @Bean
    public TelegramBot telegramBot(@Value("${telegram.token}") String token) {
        return new TelegramBot(token);
    }
}

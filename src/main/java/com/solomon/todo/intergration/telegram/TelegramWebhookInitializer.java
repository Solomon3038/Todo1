package com.solomon.todo.intergration.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class TelegramWebhookInitializer implements ApplicationListener<ServletWebServerInitializedEvent> {

    private TelegramBot bot;

    private WebApplicationContext ctx;

    public void setWebhook() {

        SetWebhook request = new SetWebhook();
        BaseResponse response = bot.execute(request);
    }

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            log.info("HostName: {}", localHost.getCanonicalHostName());
            log.info("{}, {}", localHost.getHostAddress(), localHost.getHostName());
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
    }
}

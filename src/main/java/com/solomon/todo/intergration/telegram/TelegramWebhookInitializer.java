package com.solomon.todo.intergration.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class TelegramWebhookInitializer implements ApplicationListener<ServletWebServerInitializedEvent> {

    private final TelegramBot bot;

    private final URI webhookHost;

    private final String path;

    public TelegramWebhookInitializer(
            TelegramBot bot,
            @Value("${telegram.webhook.host}") URI webhookHost,
            @Value("${telegram.webhook.path}") String path
    ) {
        this.bot = bot;
        this.webhookHost = webhookHost;
        this.path = path.startsWith("/") ? path.substring(1) : path;
    }


    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        String uri = UriComponentsBuilder.fromUri(webhookHost).pathSegment(path).build().toString();
        SetWebhook request = new SetWebhook().url(uri);
        BaseResponse response = bot.execute(request);
        log.info("URL: {}, Response {}", uri, response);
    }
}

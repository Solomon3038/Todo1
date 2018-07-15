package com.solomon.todo.intergration.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class TelegramWebhookInitializer implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Autowired
    private TelegramBot bot;

    private WebApplicationContext ctx;

    @Value("${telegram.webhook.host}")
    private URI webhookHost;

    @Value("${telegram.webhook.path}")
    private String path;

    public void setWebhook() {


    }

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        String  uri = UriComponentsBuilder.fromUri(webhookHost).pathSegment(path).build().toString();
        SetWebhook request = new SetWebhook().url(uri);
        BaseResponse response = bot.execute(request);
        log.info("URL: {}, Response {}", uri, response);
    }
}

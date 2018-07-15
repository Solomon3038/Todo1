package com.solomon.todo.intergration.telegram;

import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class TelegramUpdateController {

    @PostMapping("/telegram/update")
    public ResponseEntity getUpdate(Update update) {
        log.info(update.toString());
        return ok().build();
    }
}

package com.solomon.todo.intergration.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class TelegramUpdateController {

    @PostMapping("${telegram.webhook.path}")
    public ResponseEntity getUpdate(@RequestBody String  update) {
        log.info(update.toString());
        return ok().build();
    }
}

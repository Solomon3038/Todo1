package com.solomon.todo.intergration.telegram;

import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.intergration.telegram.command.Command;
import com.solomon.todo.intergration.telegram.converter.CommandConverter;
import com.solomon.todo.service.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.ResolvableType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

import static org.springframework.core.ResolvableType.forClassWithGenerics;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class TelegramUpdateController {

    private final ListableBeanFactory beanFactory;
    private final CommandConverter<Command> commandConverter;

    public TelegramUpdateController(ListableBeanFactory beanFactory, CommandConverter<Command> commandConverter) {
        this.beanFactory = beanFactory;
        this.commandConverter = commandConverter;
    }

    @PostMapping("${telegram.webhook.path}")
    public ResponseEntity getUpdate(@RequestBody Update update) {
        log.info(update.toString());
        Command command = commandConverter.convert(update);
        ResolvableType commandHandlerType = forClassWithGenerics(CommandHandler.class, command.getClass());
        String[] commandHandlerBeanNames = beanFactory.getBeanNamesForType(commandHandlerType);
        // FIXME: 29/07/18 стрим по идеи должен содержать ответы от CommandHandler'ов
        Stream
                .of(commandHandlerBeanNames)
                .map(name -> beanFactory.getBean(name, CommandHandler.class))
                .forEach(handler -> handler.handle(command));
        return ok().build();
    }
}

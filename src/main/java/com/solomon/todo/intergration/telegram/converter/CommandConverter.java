package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.intergration.telegram.command.Command;
import org.springframework.core.convert.converter.Converter;

public interface CommandConverter<C extends Command> extends Converter<Update, C> {

    boolean support(Update message);

}

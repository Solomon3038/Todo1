package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.intergration.telegram.command.Command;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Component
@Primary
public class CompositeCommandConverter implements CommandConverter<Command> {

    private final Collection<CommandConverter<? extends Command>> converters;

    public CompositeCommandConverter(Collection<CommandConverter<? extends Command>> converters) {
        this.converters = converters;
    }

    @Override
    public Command convert(Update update) {
        CommandConverter<? extends Command> converter = converters
                .stream()
                .filter(c -> c.support(update))
                .collect(collectingAndThen(toList(), converterSet -> {
                    if (converterSet.size() == 1) {
                        return converterSet.get(0);
                    }
                    if (converterSet.size() > 0) {
                        throw new RuntimeException("Not unique converter");
                    }
                    throw new RuntimeException("Converter not found");
                }));
        return converter.convert(update);
    }

    @Override
    public boolean support(Update message) {
        return true;
    }
}

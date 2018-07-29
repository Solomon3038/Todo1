package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.entity.Tag;
import com.solomon.todo.intergration.telegram.command.SaveRecordCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class SaveRecordCommandConverter implements CommandConverter<SaveRecordCommand> {

    @Override
    public SaveRecordCommand convert(Update update) {
        String message = update.message().text();
        return SaveRecordCommand
                .builder()
                .message(extractText(message))
                .tags(extractTags(message))
                .createdDate(LocalDateTime.now())
                .build();
    }

    private Set<Tag> extractTags(String message) {
        Set<Tag> tags = new HashSet<>();
        Pattern regexp = Pattern.compile(Tag.PREFIX + "([A-zА-я_\\d]+)");
        Matcher matcher = regexp.matcher(message);
        while (matcher.find()) {
            String tag = matcher.group(1);
            tags.add(new Tag().setName(tag));
        }
        return tags;
    }


    private String extractText(String text) {
        Matcher matcher = Pattern.compile("^/\\w+\\s+(.+)$").matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        // FIXME: 29/07/18 заменить на специфичный класс для исключения, когда невозможно распарсить текст сообщения
        throw new RuntimeException();
    }

    @Override
    public boolean support(Update message) {
        return message.message() != null && message.message().text().startsWith("/save");
    }

}


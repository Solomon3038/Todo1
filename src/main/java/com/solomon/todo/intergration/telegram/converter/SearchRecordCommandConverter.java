package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.entity.Tag;

import com.solomon.todo.intergration.telegram.command.SearchRecordByTagsCommand;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pengrad.telegrambot.model.MessageEntity.Type.bot_command;

public class SearchRecordCommandConverter implements CommandConverter<SearchRecordByTagsCommand> {
    @Override
    public boolean support(Update update) {
        Message message = update.message();
        if (message != null) {
            for (MessageEntity messageEntity : message.entities()) {
                Integer commandOffset = messageEntity.offset();
                Integer commandLength = messageEntity.length();
                if (messageEntity.type() == bot_command
                        && message.text().substring(commandOffset, commandLength).equalsIgnoreCase("/search")) {
                    // Hello #world; offset = 5, length = 6
                    return true;
                }
            }
        }
        return false;
    }

    private Set<String> extractTags(String message) {
        Set<String> tags = new HashSet<>();
        Pattern regexp = Pattern.compile(Tag.PREFIX + "([A-zА-я_\\d]+)");
        Matcher matcher = regexp.matcher(message);
        while (matcher.find()) {
            String tag = matcher.group(1);
            tags.add(tag);
        }
        return tags;
    }


    @Override
    public SearchRecordByTagsCommand convert(Update update) {

        String message = update.message().text();
        return new SearchRecordByTagsCommand(extractTags(message));

    }
}

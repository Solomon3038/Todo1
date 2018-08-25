package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.entity.Tag;

import com.solomon.todo.intergration.telegram.command.SearchRecordByTagsCommand;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchRecordCommandConverter implements CommandConverter<SearchRecordByTagsCommand> {
    @Override
    public boolean support(Update message) {
        return message.message() != null && message.message().text().startsWith("/search");
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




    @Override
    public SearchRecordByTagsCommand convert(Update update) {

        String message = update.message().text();
        return SearchRecordByTagsCommand
                .builder()
                .tag(extractTags(message))
                .build();

    }
}

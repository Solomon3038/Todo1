package com.solomon.todo.intergration.telegram.command;

import com.solomon.todo.entity.Record;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
public class SearchRecordByTagsCommand extends Command<List<Record>> {

    private final List<String> tags;

    public SearchRecordByTagsCommand(Collection<String> tags) {
        this.tags = new ArrayList<>(tags);
    } //

    public SearchRecordByTagsCommand(String... tags) {
        this.tags = asList(tags);
    }


    public static Object builder() {

        return null;
    }
}

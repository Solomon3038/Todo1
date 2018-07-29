package com.solomon.todo.intergration.telegram.command;

import com.solomon.todo.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@ToString
public class SaveRecordCommand extends Command {

    private final String message;

    @Singular
    private final Set<Tag> tags;

    private final LocalDateTime createdDate;
}

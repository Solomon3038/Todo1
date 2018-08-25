package com.solomon.todo.intergration.telegram.command;

import com.solomon.todo.entity.Record;
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
public class SaveRecordCommand extends Command<Record> {

    private final String message;

    @Singular // osnachaet dobovlenie elementov v collection po odnomu a ne srazu vsü collection i vsü kolekciü
    private final Set<Tag> tags;

    private final LocalDateTime createdDate;
}

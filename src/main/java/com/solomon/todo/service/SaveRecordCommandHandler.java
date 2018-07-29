package com.solomon.todo.service;

import com.solomon.todo.entity.Record;
import com.solomon.todo.intergration.telegram.command.SaveRecordCommand;
import com.solomon.todo.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SaveRecordCommandHandler implements CommandHandler<SaveRecordCommand> {

    private final RecordRepository recordRepository;

    public SaveRecordCommandHandler(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void handle(SaveRecordCommand command) {
        log.info("Handle command {}", command);
        Record record = new Record()
                .setId(UUID.randomUUID())
                .setText(command.getMessage())
                .setTags(command.getTags())
                .setCreateDate(command.getCreatedDate());
        recordRepository.save(record);
    }
}

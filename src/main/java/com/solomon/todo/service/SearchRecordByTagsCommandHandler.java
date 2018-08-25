package com.solomon.todo.service;


import com.solomon.todo.entity.Record;
import com.solomon.todo.intergration.telegram.command.SearchRecordByTagsCommand;
import com.solomon.todo.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchRecordByTagsCommandHandler implements CommandHandler<List<Record>, SearchRecordByTagsCommand> {

    private final RecordRepository recordRepository;

    public SearchRecordByTagsCommandHandler(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> handle(SearchRecordByTagsCommand command) {
        return recordRepository.findByTags(command.getTags());
    }
}

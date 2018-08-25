package com.solomon.todo.service;

import com.solomon.todo.entity.Record;
import com.solomon.todo.entity.Tag;
import com.solomon.todo.intergration.telegram.command.SearchRecordByTagsCommand;
import com.solomon.todo.repository.RecordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SearchRecordByTagsCommandHandlerTest.TestConfig.class)
public class SearchRecordByTagsCommandHandlerTest {

    public static final UUID RECORD_ID = UUID.randomUUID();
    public static final List<String> TAGS = asList("test", "sample");

    @Autowired
    private CommandHandler<List<Record>, SearchRecordByTagsCommand> commandHandler;

    @Test
    public void name() {
        List<Record> records = commandHandler.handle(new SearchRecordByTagsCommand(TAGS));
        assertThat(records).allMatch(record -> TAGS.stream().map(Tag.PREFIX::concat).allMatch(record.getText()::contains));

    }

    @TestConfiguration
    static class TestConfig {

        // Optional<Record> record = recordRepository.findId("1");
        //
        @Bean
        public RecordRepository recordRepository() {
            RecordRepository repository = mock(RecordRepository.class);
            when(repository.findByTags(eq(TAGS))).thenReturn(asList(new Record().setId(RECORD_ID).setText("Hi #test #sample")));
            return repository;
        }

                @Bean
        public CommandHandler<List<Record>, SearchRecordByTagsCommand> commandHandler(RecordRepository recordRepository) {
            return new SearchRecordByTagsCommandHandler(recordRepository);
        }


    }
}
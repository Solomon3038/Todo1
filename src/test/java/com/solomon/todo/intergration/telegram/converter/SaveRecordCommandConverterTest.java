package com.solomon.todo.intergration.telegram.converter;

import com.solomon.todo.entity.Tag;
import com.solomon.todo.intergration.telegram.command.SaveRecordCommand;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

import static org.assertj.core.api.Assertions.assertThat;

public class SaveRecordCommandConverterTest {

    Converter<String, SaveRecordCommand> commandConverter = new SaveRecordCommandConverter();

    @Test
    public void convert() {
        SaveRecordCommand command = commandConverter.convert("/save Hello world #test ");
        assertThat(command).extracting(SaveRecordCommand::getMessage).isEqualTo("Hello world");
        assertThat(command).extracting(SaveRecordCommand::getTags).containsOnly(new Tag("test"));
    }
}
package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.intergration.telegram.command.SaveRecordCommand;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

@Ignore
public class SaveRecordCommandConverterTest {

    Converter<Update, SaveRecordCommand> commandConverter = new SaveRecordCommandConverter();

    @Test
    public void convert() {
//        SaveRecordCommand command = commandConverter.convert("/save Hello world #test ");
//        assertThat(command).extracting(SaveRecordCommand::getMessage).isEqualTo("Hello world");
//        assertThat(command).extracting(SaveRecordCommand::getTags).containsOnly(new Tag("test"));
    }
}
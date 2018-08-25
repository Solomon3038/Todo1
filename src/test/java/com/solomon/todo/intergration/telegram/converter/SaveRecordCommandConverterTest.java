package com.solomon.todo.intergration.telegram.converter;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.solomon.todo.entity.Tag;
import com.solomon.todo.intergration.telegram.command.SaveRecordCommand;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class SaveRecordCommandConverterTest {

    Converter<Update, SaveRecordCommand> commandConverter = new SaveRecordCommandConverter();

    @Test
    public void convert() {
        Update update = mock(Update.class);
        Message message= mock(Message.class);
        when(message.text()).thenReturn("/save Hello world #test ");
        when(update.message()).thenReturn(message);
        SaveRecordCommand command = commandConverter.convert(update);
        assertThat(command).extracting(SaveRecordCommand::getMessage).isEqualTo("Hello world");
        assertThat(command).extracting(SaveRecordCommand::getTags).containsOnly(new Tag("test"));
    }
}
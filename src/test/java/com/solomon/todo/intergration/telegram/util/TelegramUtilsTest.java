package com.solomon.todo.intergration.telegram.util;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelegramUtilsTest {

    @Test
    public void extractEntity() {
        {
            // /search
            Message message = mock(Message.class);
            MessageEntity messageEntity = mock(MessageEntity.class);
            when(message.text()).thenReturn("/search");
            when(messageEntity.type()).thenReturn(MessageEntity.Type.bot_command);
            when(messageEntity.offset()).thenReturn(0);
            when(messageEntity.length()).thenReturn(6);
            when(message.entities()).thenReturn(new MessageEntity[]{messageEntity});
            List<String> commands = TelegramUtils.extractEntity(message, MessageEntity.Type.bot_command);
            assertThat(commands).containsOnlyOnce("/search");
        }
        {
            // #hi world #test message
            Message message = mock(Message.class);
            List<String> commands = TelegramUtils.extractEntity(message, MessageEntity.Type.hashtag);
            assertThat(commands).containsOnlyOnce("#test", "#hi");
        }
    }
}
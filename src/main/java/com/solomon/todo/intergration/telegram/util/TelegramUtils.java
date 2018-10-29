package com.solomon.todo.intergration.telegram.util;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class TelegramUtils {
    private TelegramUtils() {
    }

    /**
     * Достает из сообщения все сущности, выбранного типа
     *
     * @param message    телеграм сообщение
     * @param entityType искомый тип сущности (хэштэги, команды, т.д)
     * @return список сущностей в том же порядке, в котором они следуют в сообщении
     */
    public static List<String> extractEntity(Message message, MessageEntity.Type entityType) {
        List<String> enteties = new ArrayList<>();
        MessageEntity[] messageEntetie = message.entities();
        String text = message.text();
        for (MessageEntity entety : messageEntetie) {
            if (entety.type() == entityType) {
                String entetyText = text.substring(entety.offset(), entety.length());
                enteties.add(entetyText);
            }

        }
        return enteties;
    }
}

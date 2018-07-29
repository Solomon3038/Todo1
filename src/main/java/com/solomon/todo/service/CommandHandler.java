package com.solomon.todo.service;

import com.solomon.todo.intergration.telegram.command.Command;

public interface CommandHandler<C extends Command> {

    // TODO: 29/07/18 заменить void на выходные сообщения
    void handle(C command);
}

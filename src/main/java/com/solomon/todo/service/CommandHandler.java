package com.solomon.todo.service;

import com.solomon.todo.intergration.telegram.command.Command;

public interface CommandHandler<R, C extends Command<R>> {

    R handle(C command);
}

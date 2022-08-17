package ru.zalupa.command.context;

import ru.zalupa.command.Command;

import java.util.List;

public interface CommandContext {

    List<String> getOriginalArgs();

    List<Object> getArgs();

    <T> T getArg(int index);

    boolean hasArg(int index);

    Command getCommand();

    void sendMessage(String message, Object... params);

    static CommandContextImpl.ContextBuilder builder(Command command) {
        return new CommandContextImpl.ContextBuilder(command);
    }

}

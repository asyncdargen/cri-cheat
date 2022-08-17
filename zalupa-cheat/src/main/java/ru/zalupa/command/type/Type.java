package ru.zalupa.command.type;

import ru.zalupa.command.CommandParseException;

public interface Type<T> {

    T parse(String input) throws CommandParseException;

}

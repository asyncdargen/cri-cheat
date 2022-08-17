package ru.zalupa.command.type;

import ru.zalupa.command.CommandParseException;

public class TypeBoolean implements Type<Boolean> {

    public Boolean parse(String input) throws CommandParseException {
        return input.equals("1") || input.equalsIgnoreCase("t") || Boolean.parseBoolean(input);
    }

}

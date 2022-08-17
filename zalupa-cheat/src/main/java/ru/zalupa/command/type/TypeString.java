package ru.zalupa.command.type;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.zalupa.command.CommandParseException;

@NoArgsConstructor
@AllArgsConstructor
public class TypeString implements Type<String> {

    private Integer min = Integer.MIN_VALUE;
    private Integer max = Integer.MAX_VALUE;

    public String parse(String input) throws CommandParseException {
        if (input.length() > max)
            throw new CommandParseException("Длинна строки не должна быть больше §c" + max);
        if (input.length() < min)
            throw new CommandParseException("Длинна строки не должна быть меньше §c" + min);
        return input;
    }
}

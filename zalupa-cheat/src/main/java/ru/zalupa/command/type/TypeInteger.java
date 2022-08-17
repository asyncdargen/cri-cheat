package ru.zalupa.command.type;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.zalupa.command.CommandParseException;

@NoArgsConstructor
@AllArgsConstructor
public class TypeInteger implements Type<Integer> {

    private Integer min = Integer.MIN_VALUE;
    private Integer max = Integer.MAX_VALUE;

    public Integer parse(String input) throws CommandParseException {
        try {
            Integer out = Integer.parseInt(input);
            if (out > max)
                throw new CommandParseException("Число не должно быть больше §c" + max);
            if (out < min)
                throw new CommandParseException("Число не должно быть меньше §c" + min);
            return out;
        } catch (NumberFormatException e) {
            throw new CommandParseException("§c" + input + " §fне является целым числом");
        } catch (CommandParseException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            throw new CommandParseException("Произошла неизвестная ошибка, обратитесь к разработчикам");
        }
    }
}

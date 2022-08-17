package ru.zalupa.command.parameter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.zalupa.command.type.Type;

@Getter
@RequiredArgsConstructor
public class Parameter<T extends Type<?>> {

    private final String name;
    private final T type;
    private boolean required = true;

    public Parameter(String name, T type, boolean required) {
        this(name, type);
        this.required = required;
    }


}

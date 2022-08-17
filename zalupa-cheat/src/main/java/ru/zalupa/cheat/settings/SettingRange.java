package ru.zalupa.cheat.settings;

import dev.xdark.clientapi.config.Config;
import lombok.Builder;
import lombok.Getter;
import ru.zalupa.gui.GuiElement;
import ru.zalupa.gui.impl.Label;
import ru.zalupa.gui.impl.Range;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@Builder
public /*abstract*/ class SettingRange<T> implements Setting<T>  {

    protected String name;
    protected List<T> elements;
    @Builder.Default
    protected boolean prefix = false;
    @Builder.Default
    protected int index = 0;
    @Builder.Default
    protected Consumer<SettingRange<T>> changeHandler = s -> {};
    @Builder.Default
    protected Function<SettingRange<T>, String> nameFunction = s -> s.getValue().toString();

    public T getValue() {
        return elements.get(fixIndex());
    }

    public GuiElement[] getElements() {
        Range<T> range = new Range<T>()
                .setElements(elements)
                .setCurrent(index)
                .setChangeHandler(b -> {
                    index = b.getCurrent();
                    if (changeHandler != null)
                        changeHandler.accept(this);
                });
        return prefix ? new GuiElement[]{new Label().setText(name), range} : new GuiElement[]{range};
    }

    public void save(String path, Config config) {
        config.set(path + name.toLowerCase().replace(" ", "_"), index);
    }

    public void load(String path, Config config) {
        index = config.getInteger(path + name.toLowerCase().replace(" ", "_"), index);
    }

    protected int fixIndex() {
        if (index < 0) index = 0;
        else if (index >= elements.size()) index = elements.size() - 1;
        return index;
    }

}

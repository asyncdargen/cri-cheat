package ru.zalupa.cheat.settings;

import dev.xdark.clientapi.config.Config;
import ru.zalupa.gui.GuiElement;

public interface Setting<T> {

    String getName();

    T getValue();

    GuiElement[] getElements();

    void save(String path, Config config);

    void load(String path, Config config);

//    Consumer<Setting<T>> getChangeHandler();
//
//    Function<Setting<T>, String> getNameFunction();

}

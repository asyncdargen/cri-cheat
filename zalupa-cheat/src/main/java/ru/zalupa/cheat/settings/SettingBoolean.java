package ru.zalupa.cheat.settings;

import dev.xdark.clientapi.config.Config;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.zalupa.gui.GuiElement;
import ru.zalupa.gui.impl.Button;
import ru.zalupa.gui.impl.Label;

import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@Setter
@Builder
public class SettingBoolean implements Setting<Boolean> {

    protected String name;
    @Builder.Default
    protected boolean prefix = false;
    @Builder.Default
    protected boolean value = false;
    @Builder.Default
    protected Consumer<SettingBoolean> changeHandler = s -> {};
    @Builder.Default
    protected Function<SettingBoolean, String> nameFunction = s -> s.value ? "Enabled" : "Disabled";

    public GuiElement[] getElements() {
        Button button = new Button()
                .setText(nameFunction.apply(this))
                .setClickHandler(b -> {
                    value = !value;
                    b.setText(nameFunction.apply(this));
                    if (changeHandler != null)
                        changeHandler.accept(this);
                });
        return prefix ? new GuiElement[]{new Label().setText(name), button} : new GuiElement[]{button};
    }

    public void save(String path, Config config) {
        config.set(path + name.toLowerCase().replace(" ", "_"), value);
    }

    public void load(String path, Config config) {
        value = config.getBoolean(path + name.toLowerCase().replace(" ", "_"), value);
    }

    public Boolean getValue() {
        return value;
    }

}

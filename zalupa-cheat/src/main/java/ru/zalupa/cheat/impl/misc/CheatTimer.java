package ru.zalupa.cheat.impl.misc;

import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;

public class CheatTimer extends Cheat {

    protected SettingRange<Double> multiplier;

    public CheatTimer() {
        super("Timer");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                multiplier = SettingRange.<Double>builder()
                        .name("Multiplier")
                        .elements(Range.range(.1, 3, .1))
                        .index(9).prefix(true)
                        .changeHandler(s -> onToggle()).build()
        };
    }

    public float getTickLength() {
        return 50f * multiplier.getValue().floatValue();
    }

    public void onEnable() {
        Zalupa.getZalupa().getClientApi().minecraft().getTimer().setTickLength(getTickLength());
    }

    public void onDisable() {
        Zalupa.getZalupa().getClientApi().minecraft().getTimer().setTickLength(50);
    }

    public void onRegister() {}
}

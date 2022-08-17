package ru.zalupa.cheat.impl.movement;

import dev.xdark.clientapi.event.lifecycle.GameLoop;
import dev.xdark.clientapi.inventory.Container;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;

import java.awt.*;

public class CheatAutoSneakSprint extends Cheat {

    protected SettingBoolean mode;

    public CheatAutoSneakSprint() {
        super("AutoSS");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                mode = SettingBoolean.builder()
                        .name("Mode").value(true)
                        .nameFunction(s -> s.getValue() ? "Sprint" : "Sneak")
                        .prefix(true).build()
        };
    }

    public void onRegister() {
        GameLoop.BUS.register(event -> {
            if (!isEnabled() || !Zalupa.isOnline())
                return;

            if (mode.getValue() && !Zalupa.getSelfPlayer().isSprinting())
                Zalupa.getSelfPlayer().setSprinting(true);
            else Zalupa.getSelfPlayer().setSneaking(true);
        });
    }
}

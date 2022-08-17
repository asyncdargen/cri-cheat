package ru.zalupa.cheat.impl.combat;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.util.RobotUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;

import java.awt.event.InputEvent;
import java.util.Arrays;

public class CheatAutoClicker extends Cheat {

    protected SettingRange<Integer> cps;
    protected SettingRange<Mode> mode;

    public CheatAutoClicker() {
        super("AutoClicker");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                cps = SettingRange.<Integer>builder()
                        .name("CPS")
                        .elements(Range.range(1, 100, 1))
                        .index(0).prefix(true).build(),
                mode = SettingRange.<Mode>builder()
                        .name("Mode")
                        .elements(Arrays.asList(Mode.values()))
                        .prefix(true).build()
        };
    }

    @Override
    public void onRegister() {
        Zalupa.getZalupa().getClientApi().threadManagement().newThread(new Runnable() {
            @SneakyThrows
            public void run() {
                while (true) {
                    EntityPlayerSP self = Zalupa.getSelfPlayer();
                    if (isEnabled() && self != null && Zalupa.getApi().minecraft().currentScreen() == null)
                        RobotUtil.pressMouse(mode.getValue() == Mode.LEFT ? 1 << 10 : 1 << 12);

                    Thread.sleep((long) (1000d / cps.getValue()));
                }
            }
        }).start();
    }

    @AllArgsConstructor
    enum Mode {
        LEFT("Left"),
        RIGHT("Right");

        private final String name;

        public String toString() {
            return name;
        }
    }
}

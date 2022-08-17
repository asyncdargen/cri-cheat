package ru.zalupa.cheat.impl.movement;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.entity.PlayerJump;
import dev.xdark.clientapi.math.MathHelper;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;

public class CheatLongJump extends Cheat {

    protected SettingRange<Double> multiplier;
    protected SettingRange<Double> yMultiplier;

    public CheatLongJump() {
        super("LongJump");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                multiplier = SettingRange.<Double>builder()
                        .name("Multiplier")
                        .elements(Range.range(.1, 3, .1))
                        .index(0).prefix(true).build(),
                yMultiplier = SettingRange.<Double>builder()
                        .name("Y-Multiplier")
                        .elements(Range.range(.5, 3, .1))
                        .index(0).prefix(true).build()
        };
    }

    private void on(PlayerJump event) {
        if (!isEnabled()) return;
        EntityPlayerSP player = Zalupa.getZalupa().getClientApi().minecraft().getPlayer();
        float yaw = player.getRotationYaw() / 180.0F * 3.1415927F;
        double cos = MathHelper.cos(yaw);
        double sin = MathHelper.sin(yaw);
        double mp = multiplier.getValue();
        player.setMotion(-sin * mp, yMultiplier.getValue(), cos * mp);
    }
    public void onRegister() {
        PlayerJump.BUS.register(this::on);
    }
}

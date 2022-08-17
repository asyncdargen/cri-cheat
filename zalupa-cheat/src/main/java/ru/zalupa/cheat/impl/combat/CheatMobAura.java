package ru.zalupa.cheat.impl.combat;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.lifecycle.GameLoop;
import dev.xdark.clientapi.event.lifecycle.GameTickPost;
import dev.xdark.clientapi.game.Minecraft;
import dev.xdark.clientapi.original.IDargen;
import dev.xdark.clientapi.text.Text;
import lombok.SneakyThrows;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;
import ru.zalupa.util.EntityManager;
import ru.zalupa.util.RenderTools;

public class CheatMobAura extends Cheat {

    protected SettingRange<Integer> range;
    protected SettingRange<Integer> cps;

    public CheatMobAura() {
        super("MobAura");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                range = SettingRange.<Integer>builder()
                        .name("Range")
                        .elements(Range.range(1, 8, 1))
                        .index(0).prefix(true).build(),
                cps = SettingRange.<Integer>builder()
                        .name("CPS")
                        .elements(Range.range(1, 100, 1))
                        .index(0).prefix(true).build()
        };
    }

    @SneakyThrows
    public void onRegister() {
        EntityManager em = Zalupa.getZalupa().getEntityManager();
        Zalupa.getZalupa().getClientApi().threadManagement().newThread(new Runnable() {
            @SneakyThrows
            public void run() {
                while (true) {
                    EntityPlayerSP self = Zalupa.getSelfPlayer();

                    if (isEnabled() && self != null) {
                        IDargen dargen = Zalupa.getDargen();
                        em.getMobs().forEach(entity -> {
                            if (Math.sqrt(Math.pow(entity.getX() - self.getX(), 2) + Math.pow(entity.getY() - self.getY(), 2) + Math.pow(entity.getZ() - self.getZ(), 2)) > range.getValue())
                                return;

                            dargen.attack(entity);
                        });
                    }

                    Thread.sleep((long) (1000d / cps.getValue()));
                }
            }
        }).start();
    }
}

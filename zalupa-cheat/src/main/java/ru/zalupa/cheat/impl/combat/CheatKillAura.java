package ru.zalupa.cheat.impl.combat;

import dev.xdark.clientapi.entity.EntityPlayer;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.lifecycle.GameLoop;
import dev.xdark.clientapi.original.IDargen;
import dev.xdark.clientapi.util.RobotUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;
import ru.zalupa.util.EntityManager;
import ru.zalupa.util.RenderTools;

import java.util.Arrays;

public class CheatKillAura extends Cheat {

    protected SettingRange<Integer> range;
    protected SettingRange<Integer> cps;
    protected SettingRange<Mode> mode;

    public CheatKillAura() {
        super("KillAura");
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
                        .index(0).prefix(true).build(),
                mode = SettingRange.<Mode>builder()
                        .name("Mode")
                        .elements(Arrays.asList(Mode.values()))
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
                        em.getPlayers().forEach(entity -> {
                            if (entity == self || Math.sqrt(Math.pow(entity.getX() - self.getX(), 2) + Math.pow(entity.getY() - self.getY(), 2) + Math.pow(entity.getZ() - self.getZ(), 2)) > range.getValue())
                                return;
                            if (mode.getValue().checkEntity(entity, self))
                                dargen.attack(entity);
                        });
                    }

                    Thread.sleep((long) (1000d / cps.getValue()));
                }
            }
        }).start();
    }

    @AllArgsConstructor
    private enum Mode {
        DEFAULT("Default"),
        CHEACK_TEAM("Check Team (Beta)") {
            public boolean checkEntity(EntityPlayer entity, EntityPlayer self) {
                //Very bad check ;|
                String snt = self.getDisplayName() == null ? "" : self.getDisplayName().toString() == null ? "" : self.getDisplayName().toString();
                String pnt = entity.getDisplayName() == null ? "" : entity.getDisplayName().toString() == null ? "" : entity.getDisplayName().toString();
                return snt.length() >= 2 && pnt.startsWith(snt.substring(0, 2));
            }
        },
        CHECK_FRIENDS("Check Friends") {
            public boolean checkEntity(EntityPlayer entity, EntityPlayer self) {
                return !Zalupa.getZalupa().getFriendManager().isFriend(entity);
            }
        };

        protected final String name;

        public String toString() {
            return name;
        }

        public boolean checkEntity(EntityPlayer entity, EntityPlayer self) {
            return true;
        }
    }
}

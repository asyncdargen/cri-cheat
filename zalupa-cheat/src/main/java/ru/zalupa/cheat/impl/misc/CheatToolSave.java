package ru.zalupa.cheat.impl.misc;

import dev.xdark.clientapi.event.lifecycle.GameLoop;
import dev.xdark.clientapi.gui.Screen;
import dev.xdark.clientapi.gui.ingame.InventorySurvivalScreen;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.util.RobotUtil;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;

public class CheatToolSave extends Cheat {

    protected SettingRange<Integer> damage;

    public CheatToolSave() {
        super("ToolSave");
    }

    @Override
    public Setting<?>[] getSettings() {
        return new Setting[] {
                damage = SettingRange.<Integer>builder()
                        .name("Damage")
                        .elements(Range.range(1, 10, 1))
                        .index(0).prefix(true).build()
        };
    }

    @Override
    public void onRegister() {
        GameLoop.BUS.register(gameLoop -> {
            if (!isEnabled())
                return;

            ItemStack stack = Zalupa.getSelfPlayer().getHeldItemMainhand();

            if (stack.getMaxDamage() - stack.getItemDamage() <= damage.getValue() && stack.getItemDamage() != 0)
                switchSlot(Zalupa.getSelfPlayer().getInventory().getActiveSlot());
        });
    }

    public void switchSlot(int slot) {
        if (Zalupa.getApi().minecraft().currentScreen() instanceof InventorySurvivalScreen)
            Zalupa.getSelfPlayer().closeScreen();

        if (slot >= 0 && slot < 9)
            RobotUtil.pressKey(49 + (slot + 1 > 8 ? 0 : ++slot));
    }
}

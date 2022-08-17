package ru.zalupa.cheat.impl.misc;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.network.ServerConnect;
import dev.xdark.clientapi.potion.Potion;
import dev.xdark.clientapi.potion.PotionEffect;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;

public class CheatEffects extends Cheat {

    protected SettingRange<Integer> haste;
    protected SettingBoolean nVision;
//    protected SettingBoolean antiNegative; //dont working

    protected final Potion hastePotion;
    protected final Potion nVisionPotion;

    public CheatEffects() {
        super("Effects");

        hastePotion = Zalupa.getZalupa().getClientApi().potionRegistry().getPotion(3);
        nVisionPotion = Zalupa.getZalupa().getClientApi().potionRegistry().getPotion(16);
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                haste = SettingRange.<Integer>builder()
                        .name("Haste Level")
                        .elements(Range.range(0, 30, 1))
                        .index(0).prefix(true).changeHandler(s -> {
                            onDisable();
                            if (isEnabled() && s.getValue() > 0)
                                onEnable();
                        }).build(),
                nVision = SettingBoolean.builder()
                        .name("Night Vision").prefix(true)
                        .changeHandler(s -> {
                            onDisable();
                            if (isEnabled())
                                onEnable();
                        }).build(),
//                antiNegative = SettingBoolean.builder()
//                        .name("Disable Negative")
//                        .value(false).prefix(true).build()
        };
    }


    public void onEnable() {
        EntityPlayerSP player = Zalupa.getSelfPlayer();
        if (player == null) return;
        if (haste.getValue() > 0)
            player.addPotionEffect(hastePotion.newEffect(25, haste.getValue() - 1, false, false));
        if (nVision.getValue())
            player.addPotionEffect(nVisionPotion.newEffect(25, 0, false, false));
    }

    public void onDisable() {
        EntityPlayerSP player = Zalupa.getSelfPlayer();
        if (player == null) return;
        player.removePotionEffect(hastePotion);
        player.removeActivePotionEffect(hastePotion);
        player.removePotionEffect(nVisionPotion);
        player.removeActivePotionEffect(nVisionPotion);
    }

    public void onRegister() {
        ServerConnect.BUS.register(event -> {
            onDisable();
            if (isEnabled()) {
                onEnable();
            } else onDisable();
        });
    }
}


package ru.zalupa.cheat.impl.render;

import dev.xdark.clientapi.event.lifecycle.GameLoop;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.util.EntityManager;

public class CheatESP extends Cheat {

    protected EntityManager entityManager = Zalupa.getZalupa().getEntityManager();

    protected SettingBoolean players;
    protected SettingBoolean mobs;
    protected SettingBoolean items;

    public CheatESP() {
        super("ESP");
    }

    private void onPlayers() {
        if (!isEnabled() || !players.getValue()) {
            entityManager.getPlayers().forEach(player -> player.setGlowing(false));
            return;
        }

        entityManager.getPlayers().forEach(player -> {
            player.setGlowing(true);
            player.setGlowColor(getIntFromColor(128, 128, 128));
        });
    }

    private void onMobs() {
        if (!isEnabled() || !mobs.getValue()) {
            entityManager.getMobs().forEach(mobs -> mobs.setGlowing(false));
            return;
        }

        entityManager.getMobs().forEach(mobs -> {
            mobs.setGlowing(true);
            mobs.setGlowColor(getIntFromColor(128, 128, 128));
        });
    }

    private void onItems() {
        if (!isEnabled() || !items.getValue()) {
            entityManager.getItems().forEach(items -> items.setGlowing(false));
            return;
        }

        entityManager.getItems().forEach(items -> {
            items.setGlowing(true);
            items.setGlowColor(getIntFromColor(128, 128, 128));
        });
    }

    public void on(GameLoop gameLoop) {
        onPlayers();
        onMobs();
        onItems();
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                players = SettingBoolean.builder()
                        .name("Players")
                        .value(false).prefix(true).build(),

                mobs = SettingBoolean.builder()
                        .name("Mobs")
                        .value(false).prefix(true).build(),

                items = SettingBoolean.builder()
                        .name("Items")
                        .value(false).prefix(true).build()
        };
    }

    public void onRegister() {
        GameLoop.BUS.register(this::on);
    }

    private int getIntFromColor(int r, int g, int b) {
        r = r << 16 & 16711680;
        g = g << 8 & '\uff00';
        b &= 255;
        return -16777216 | r | g | b;
    }
}

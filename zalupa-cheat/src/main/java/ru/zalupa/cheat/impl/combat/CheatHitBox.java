package ru.zalupa.cheat.impl.combat;

import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.lifecycle.GameLoop;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;

public class CheatHitBox extends Cheat {

    protected SettingRange<Double> players;
    protected SettingRange<Double> mobs;
    protected SettingRange<Double> items;

    public CheatHitBox() {
        super("HitBox");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                players = SettingRange.<Double>builder()
                        .name("Players")
                        .elements(Range.range(.6, 3d, .1))
                        .index(0).prefix(true).build(),
                mobs = SettingRange.<Double>builder()
                        .name("Mobs")
                        .elements(Range.range(1, 3d, .1))
                        .index(0).prefix(true).build(),
                items = SettingRange.<Double>builder()
                        .name("Items")
                        .elements(Range.range(.25, 3d, .25))
                        .index(0).prefix(true).build(),
        };
    }

    public float getPlayerSize(Entity entity) {
        if (isEnabled()) {
            if (Zalupa.getZalupa().getFriendManager().isFriend(entity))
                return 0.6F;
            return players.getValue().floatValue();
        }
        return 0.6F;
    }

    public float getMobsSize(Entity entity) {
        if (isEnabled()) {
            if (Zalupa.getZalupa().getFriendManager().isFriend(entity))
                return 1.0F;
            return mobs.getValue().floatValue();
        }
        return 1.0F;
    }

    public float getItemsSize(Entity entity) {
        if (isEnabled()) {
            if (Zalupa.getZalupa().getFriendManager().isFriend(entity))
                return 0.25F;
            return items.getValue().floatValue();
        }
        return 0.25F;
    }

    private void on(GameLoop event) {
        EntityPlayerSP player = Zalupa.getSelfPlayer();
        if (player == null)
            return;

        Zalupa.getZalupa().getEntityManager().getPlayers().forEach(entity -> {
            if (entity != player)
                if (!Zalupa.getZalupa().getFriendManager().isFriend(entity))
                    try {
                        entity.setSize(-getPlayerSize(entity), 2.0F);
                    } catch (Throwable ignored) {
                    }
        });

        Zalupa.getZalupa().getEntityManager().getMobs().forEach(entity -> {
            if (!Zalupa.getZalupa().getFriendManager().isFriend(entity))
                try {
                    entity.setSize(-getMobsSize(entity), 2.0F);
                } catch (Throwable ignored) {
                }
        });

        Zalupa.getZalupa().getEntityManager().getItems().forEach(entity -> {
            if (!Zalupa.getZalupa().getFriendManager().isFriend(entity))
                try {
                    entity.setSize(-getItemsSize(entity), 2.0F);
                } catch (Throwable ignored) {
                }
        });
    }

    public void onRegister() {
        GameLoop.BUS.register(this::on);
    }

}

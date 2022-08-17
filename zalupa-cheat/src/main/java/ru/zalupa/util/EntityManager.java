package ru.zalupa.util;

import dev.xdark.clientapi.entity.*;
import dev.xdark.clientapi.event.entity.EntityDataChange;
import dev.xdark.clientapi.event.entity.EntityDespawn;
import dev.xdark.clientapi.event.entity.EntityInit;
import dev.xdark.clientapi.event.entity.EntitySpawn;
import dev.xdark.clientapi.event.network.ServerConnect;
import dev.xdark.clientapi.event.network.ServerQuit;
import dev.xdark.clientapi.event.network.ServerSwitch;
import dev.xdark.clientapi.item.Item;
import dev.xdark.clientapi.text.Text;
import dev.xdark.clientapi.util.Collections;
import lombok.Getter;
import lombok.val;
import ru.zalupa.Zalupa;

import java.util.Set;

@Getter
public class EntityManager {

    protected Set<Entity> entities = Collections.concurrentSet();
    protected Set<EntityPlayer> players = Collections.concurrentSet();
    protected Set<EntityLiving> mobs = Collections.concurrentSet();
    protected Set<EntityItem> items = Collections.concurrentSet();

    public EntityManager() {
        EntityDespawn.BUS.register(event -> {
            val entity = event.getEntity();
            entities.remove(event.getEntity());

            if (entity instanceof EntityLiving && !(entity instanceof EntityArmorStand) && !(entity instanceof EntityEnderCrystal))
                mobs.remove((EntityLiving) entity);

            if (event.getEntity() instanceof EntityPlayer)
                players.remove((EntityPlayer) event.getEntity());
        });

        EntitySpawn.BUS.register(event -> {
            Entity entity = event.getEntity();

            if (entity != null) {
                entities.add(entity);
                if (entity instanceof EntityPlayer) {
                    if (/*((EntityPlayer) entity).isUser() && */entity != Zalupa.getZalupa().getClientApi().minecraft().getPlayer())
                        players.add((EntityPlayer) entity);
                } else if (entity instanceof EntityLiving && !(entity instanceof EntityArmorStand) && !(entity instanceof EntityEnderCrystal)) {
                    EntityLiving entityLiving = (EntityLiving) entity;

                    mobs.add(entityLiving);
                }
                else if (entity instanceof EntityItem) {
                    items.add((EntityItem) entity);
                }
            }
        });

        ServerConnect.BUS.register(event -> {
            entities.clear();
            mobs.clear();
            players.clear();
        });
        ServerQuit.BUS.register(event -> {
            entities.clear();
            mobs.clear();
            players.clear();
        });
        ServerSwitch.BUS.register(event -> {
            entities.clear();
            mobs.clear();
            players.clear();
        });

    }

}

package ru.zalupa.cheat.impl.misc;

import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.block.BlockLeftClick;
import dev.xdark.clientapi.inventory.InventoryPlayer;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.math.BlockPos;
import dev.xdark.clientapi.math.RayTraceResult;
import dev.xdark.clientapi.util.Collections;
import dev.xdark.clientapi.util.RobotUtil;
import lombok.Getter;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.util.EnchantmentUtil;

import java.util.Map;

public class CheatAutoTool extends Cheat {

    private @Getter SettingBoolean nuker;
    protected SettingBoolean mode;

    public CheatAutoTool() {
        super("AutoTool");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                nuker = SettingBoolean.builder()
                        .name("In Nuker").prefix(true)
                        .value(false).build(),
                mode = SettingBoolean.builder()
                        .name("Mode").prefix(true)
                        .nameFunction(btn -> btn.getValue() ? "On Click" : "On View")
                        .value(false).build()
        };
    }

    public void onRegister() {
        BlockLeftClick.BUS.register(event -> {
            if (Zalupa.isOnline() && isEnabled() && mode.getValue() && !nuker.getValue())
                equip(event.getPosition());
        });
        Zalupa.getApi().threadManagement().newThread(() -> {
            while (true) {
                if (Zalupa.isOnline() && isEnabled() && !mode.getValue() && !nuker.getValue()) {
                    RayTraceResult rtr = Zalupa.getApi().minecraft().getMouseOver();
                    if (rtr.getType() == RayTraceResult.Type.BLOCK && rtr.getPos() != null)
                        equip(rtr.getPos());
                }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void equip(BlockPos pos) {
        if (Zalupa.isOnline() && isEnabled())
            equipBestTool(pos, true, true);
    }

    final Map<Integer, Integer> type2slotAliases = Collections.concurrentMap();
    long hash = 0;

    protected boolean checkHashValidAndInvalidateCache(EntityPlayerSP player) {
        InventoryPlayer inventory = player.getInventory();
        long hash = 0;
        for (int slot = 0; slot < 9; slot++) {
            ItemStack item = inventory.getStackInSlot(slot);
            long h = item == null || item.isEmpty() || !item.isDamageable() || item.isStackable() ? 0 : item.hashCode();
            hash += (h - (slot + 1)) / (slot + 1);
        }
        boolean valid = this.hash == hash;

        if (!valid) {
            this.hash = hash;
            type2slotAliases.clear();
        }

        return valid;
    }

    public void equipBestTool(BlockPos pos, boolean useHands, boolean repairMode) {
        EntityPlayerSP player = Zalupa.getSelfPlayer();

        checkHashValidAndInvalidateCache(player);

        switchSlot(type2slotAliases.computeIfAbsent(player.getWorld().getBlockState(pos.getX(), pos.getY(), pos.getZ()).getId(), id -> {
            int bestSlot = getBestSlot(pos);
            if (bestSlot == -1) {
                ItemStack heldItem = player.getHeldItemMainhand();
                if (!isDamageable(heldItem))
                    return -1;

                if (repairMode && isTooDamaged(heldItem)) {
                    selectFallbackSlot();
                    return -1;
                }

                if (useHands && isWrongTool(heldItem, pos))
                    selectFallbackSlot();
            }

            return bestSlot;
        }));
    }

    private int getBestSlot(BlockPos pos) {
        EntityPlayerSP player = Zalupa.getSelfPlayer();
        InventoryPlayer inventory = player.getInventory();
        ItemStack heldItem = player.getHeldItemMainhand();

        BlockState state = player.getWorld().getBlockState(pos.getX(), pos.getY(), pos.getZ());
        float bestSpeed = getMiningSpeed(heldItem, state);
        int bestSlot = -1;

        for (int slot = 0; slot < 9; slot++) {
            if (slot == inventory.getActiveSlot())
                continue;

            ItemStack stack = inventory.getStackInSlot(slot);

            float speed = getMiningSpeed(stack, state);

            if (speed <= bestSpeed)
                continue;

            bestSpeed = speed;
            bestSlot = slot;
        }

        return bestSlot;
    }

    private float getMiningSpeed(ItemStack stack, BlockState state) {
        float speed = stack.getDestroySpeed(state);

        if (speed > 1) {
            int efficiency = EnchantmentUtil.getLevel(stack, EnchantmentUtil.EFFICIENCY);

            if (efficiency > 0 && !stack.isEmpty())
                speed += efficiency * efficiency + 1;
        }

        return speed;
    }

    private boolean isDamageable(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem().isDamageable();
    }

    private boolean isTooDamaged(ItemStack stack) {
        return stack.getMaxDamage() - stack.getItemDamage() <= 4;
    }

    private boolean isWrongTool(ItemStack heldItem, BlockPos pos) {
        BlockState state = Zalupa.getSelfPlayer().getWorld().getBlockState(pos.getX(), pos.getY(), pos.getZ());
        return getMiningSpeed(heldItem, state) <= 1;
    }

    private void selectFallbackSlot() {
        int fallbackSlot = getFallbackSlot();
        InventoryPlayer inventory = Zalupa.getSelfPlayer().getInventory();

        if (fallbackSlot == -1) {
            if (inventory.getActiveSlot() == 8)
                switchSlot(0);
            else switchSlot(inventory.getActiveSlot() + 1);
            return;
        }

        switchSlot(fallbackSlot);
    }

    private int getFallbackSlot() {
        InventoryPlayer inventory = Zalupa.getSelfPlayer().getInventory();

        for (int slot = 0; slot < 9; slot++) {
            if (slot == inventory.getActiveSlot())
                continue;

            ItemStack stack = inventory.getStackInSlot(slot);

            if (!isDamageable(stack))
                return slot;
        }

        return -1;
    }

    public void switchSlot(int slot) {
        if (slot >= 0 && slot < 9 && Zalupa.getSelfPlayer().getInventory().getActiveSlot() != slot)
            RobotUtil.pressKey(49 + slot);
    }

}

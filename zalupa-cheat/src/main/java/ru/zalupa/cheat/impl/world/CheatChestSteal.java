package ru.zalupa.cheat.impl.world;

import dev.xdark.clientapi.block.Block;
import dev.xdark.clientapi.block.Blocks;
import dev.xdark.clientapi.block.material.Material;
import dev.xdark.clientapi.event.block.BlockRightClick;
import dev.xdark.clientapi.event.lifecycle.GameLoop;
import dev.xdark.clientapi.event.render.RenderPass;
import dev.xdark.clientapi.inventory.ClickType;
import dev.xdark.clientapi.inventory.InventoryBasic;
import dev.xdark.clientapi.inventory.InventoryPlayer;
import dev.xdark.clientapi.item.Item;
import dev.xdark.clientapi.item.ItemStack;
import lombok.val;
import lombok.var;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheatChestSteal extends Cheat {

    public boolean chest;
    public List<Block> blocks;

    public CheatChestSteal() {
        super("ChestSteal");
        blocks = Arrays.asList(
                Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.BLACK_SHULKER_BOX,
                Blocks.BLUE_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX,
                Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX,
                Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX,
                Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX,
                Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX
        );
    }

    @Override
    public void onRegister() {
        BlockRightClick.BUS.register(event -> {
            if (!Zalupa.isOnline() || !isEnabled())
                return;

            val pos = event.getPosition();
            val block = Zalupa.getSelfPlayer().getWorld().getBlockState(pos.getX(), pos.getY(), pos.getZ()).getBlock();

            if (!blocks.contains(block))
                return;

            chest = true;
        });

        RenderPass.BUS.register(event -> {
            val player = Zalupa.getSelfPlayer();

            if (player != null && isEnabled() && chest) {
                var openContainer = player.getOpenContainer();

                if (openContainer == null)
                    return;

                val slots = openContainer.getSlots();
                val windowId = openContainer.getWindowId();

                if (player.getInventory().getFirstEmptyStack() == -1 || slots.isEmpty())
                    return;

                slots.forEach(slot -> {
                    val slotI = slot.getSlotNumber();

                    Zalupa.getApi().getBezdar().steal(windowId,
                            slotI, 0, ClickType.QUICK_MOVE,
                            ItemStack.of(Item.of(0), 0, 0), (short) 0
                    );
                    openContainer.slotClick(slotI, 0, ClickType.QUICK_MOVE, player);
                });

                chest = false;
            }
        });
    }
}

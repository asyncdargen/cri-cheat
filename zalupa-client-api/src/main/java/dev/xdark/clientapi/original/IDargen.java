package dev.xdark.clientapi.original;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.inventory.ClickType;
import dev.xdark.clientapi.item.ItemStack;

@SidedApi(Side.BOTH)
public interface IDargen {

    void blockAction(dev.xdark.clientapi.math.BlockPos pos,
                     dev.xdark.clientapi.original.DiggingAction action,
                     dev.xdark.clientapi.util.EnumFacing facing);

    void attack(dev.xdark.clientapi.entity.Entity entity);

    void changeGameMode(dev.xdark.clientapi.world.GameMode mode);

    void fall(boolean onGround);

    void steal(int windowId, int slot, int mouse, ClickType clickType, ItemStack itemStack, short idk);
}

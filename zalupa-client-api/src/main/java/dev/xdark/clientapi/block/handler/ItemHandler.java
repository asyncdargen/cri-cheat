package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface ItemHandler {

  ItemStack getItem(World world, int x, int y, int z, BlockState state);
}

package dev.xdark.clientapi.item;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;

@SidedApi(Side.BOTH)
public interface BlockDestroySpeedHandler {

    float getDestroySpeed(ItemStack stack, BlockState state);
}

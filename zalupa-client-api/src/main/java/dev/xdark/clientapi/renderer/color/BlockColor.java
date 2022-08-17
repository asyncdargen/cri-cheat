package dev.xdark.clientapi.renderer.color;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.math.BlockPos;
import dev.xdark.clientapi.world.BlockAccess;

@SidedApi(Side.BOTH)
public interface BlockColor {

  int colorMultiplier(BlockState state, BlockAccess blockAccess, BlockPos pos, int tintIndex);
}

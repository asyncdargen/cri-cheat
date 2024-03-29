package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.Block;
import dev.xdark.clientapi.world.BlockAccess;

@SidedApi(Side.BOTH)
public interface PassHandler {

  boolean isPassable(Block block, BlockAccess blockAccess, int x, int y, int z);
}

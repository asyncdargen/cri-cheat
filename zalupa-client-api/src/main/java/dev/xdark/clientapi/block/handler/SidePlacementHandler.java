package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.Block;
import dev.xdark.clientapi.util.EnumFacing;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface SidePlacementHandler {

  boolean canPlaceBlockOnSide(Block block, World world, int x, int y, int z, EnumFacing facing);
}

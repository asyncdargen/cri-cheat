package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.Block;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface EntityWalkHandler {

  void onEntityWalk(Block block, World world, int x, int y, int z, Entity entity);
}

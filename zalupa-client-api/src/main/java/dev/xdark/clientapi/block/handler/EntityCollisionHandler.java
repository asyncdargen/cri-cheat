package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface EntityCollisionHandler {

  void onEntityCollision(World world, int x, int y, int z, BlockState state, Entity entity);
}

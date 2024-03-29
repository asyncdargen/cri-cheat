package dev.xdark.clientapi.block;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.resource.ResourceLocation;

@SidedApi(Side.BOTH)
public interface BlockRegistry {

  Block getBlock(int id);

  BlockState getState(int id);

  Block getBlock(String idOrLocation);

  void register(int id, ResourceLocation location, Block block);
}

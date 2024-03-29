package dev.xdark.clientapi.renderer.block.statemap;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.Block;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.renderer.block.model.ModelResourceLocation;
import dev.xdark.clientapi.resource.ResourceLocation;

import java.util.Map;
import java.util.Set;

@SidedApi(Side.BOTH)
public interface BlockStateMapper {

  void registerBlockStateMapper(Block block, StateMapper stateMapper);

  void registerBuiltInBlocks(Block... blocks);

  Map<BlockState, ModelResourceLocation> putAllStateModelLocations();

  Set<ResourceLocation> getBlockStateLocations(Block block);

  Map<BlockState, ModelResourceLocation> getVariants(Block block);
}

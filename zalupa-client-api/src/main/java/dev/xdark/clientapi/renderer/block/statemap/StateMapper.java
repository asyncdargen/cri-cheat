package dev.xdark.clientapi.renderer.block.statemap;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.renderer.block.model.ModelResourceLocation;

@SidedApi(Side.BOTH)
public interface StateMapper {

    ModelResourceLocation getModelResourceLocation(BlockState state);
}

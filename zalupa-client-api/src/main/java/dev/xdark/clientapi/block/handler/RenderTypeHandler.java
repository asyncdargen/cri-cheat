package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.util.BlockRenderType;

@SidedApi(Side.BOTH)
public interface RenderTypeHandler {

  BlockRenderType getRenderType(BlockState state);
}

package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.state.BlockState;

@SidedApi(Side.BOTH)
public interface NeighborBrightnessHandler {

  boolean shouldUseNeighborBrightness(BlockState state);
}

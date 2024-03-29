package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.material.Material;
import dev.xdark.clientapi.block.state.BlockState;

@SidedApi(Side.BOTH)
public interface MaterialHandler {

    Material getMaterial(BlockState state);
}

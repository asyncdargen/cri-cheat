package dev.xdark.clientapi.block.handler;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.block.Block;
import dev.xdark.clientapi.entity.EntityPlayer;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface BlockClickHandler {

    void onBlockClicked(Block block, World world, int x, int y, int z, EntityPlayer player);
}

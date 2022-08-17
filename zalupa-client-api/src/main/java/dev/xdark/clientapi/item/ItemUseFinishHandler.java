package dev.xdark.clientapi.item;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entity.EntityLivingBase;
import dev.xdark.clientapi.util.ActionResult;
import dev.xdark.clientapi.world.World;

@SidedApi(Side.BOTH)
public interface ItemUseFinishHandler {

  ActionResult onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity);
}

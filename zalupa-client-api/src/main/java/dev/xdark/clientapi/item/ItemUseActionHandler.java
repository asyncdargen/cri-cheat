package dev.xdark.clientapi.item;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface ItemUseActionHandler {

    UseAction getUseAction(ItemStack stack);

    int getMaxUseDuration(ItemStack stack);
}

package dev.xdark.clientapi.item;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface ItemEnchantHandler {

    boolean isEnchantable(ItemStack stack);

    int getEnchantability(Item item);
}

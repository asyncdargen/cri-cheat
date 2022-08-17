package ru.zalupa.util;

import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.nbt.NBTTagCompound;
import dev.xdark.clientapi.nbt.NBTTagList;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnchantmentUtil {

    public int EFFICIENCY = 32;

    public int getLevel(ItemStack itemStack, int enchantment) {
        NBTTagList enchantmentList = itemStack.getEnchantmentTagList();
        int size = enchantmentList.size();

        for (int i = 0; i < size; i++) {
            NBTTagCompound parsed = enchantmentList.getCompound(i);

            if (parsed.getInteger("id") != enchantment)
                continue;

            return parsed.getInteger("lvl");
        }

        return 0;
    }
}

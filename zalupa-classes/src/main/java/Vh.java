import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.entity.EntityPlayer;
import dev.xdark.clientapi.item.Item;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.item.TooltipFlag;
import dev.xdark.clientapi.nbt.NBTBase;
import dev.xdark.clientapi.nbt.NBTTagCompound;
import dev.xdark.clientapi.nbt.NBTTagList;
import dev.xdark.clientapi.text.Text;

import java.util.List;

public class Vh implements ItemStack {
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack split(int i) {
        return null;
    }

    @Override
    public Item getItem() {
        return null;
    }

    @Override
    public float getDestroySpeed(BlockState blockState) {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isItemDamaged() {
        return false;
    }

    @Override
    public int getItemDamage() {
        return 0;
    }

    @Override
    public int getMetadata() {
        return 0;
    }

    @Override
    public void setItemDamage(int i) {

    }

    @Override
    public int getMaxDamage() {
        return 0;
    }

    @Override
    public ItemStack copy() {
        return null;
    }

    @Override
    public boolean isItemEqual(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean isItemEqualIgnoreDurability(ItemStack itemStack) {
        return false;
    }

    @Override
    public String getTranslationKey() {
        return null;
    }

    @Override
    public NBTTagCompound getTagCompound() {
        return null;
    }

    @Override
    public NBTTagCompound getOrCreateSubCompound(String s) {
        return null;
    }

    @Override
    public NBTTagCompound getSubCompound(String s) {
        return null;
    }

    @Override
    public void removeSubCompound(String s) {

    }

    @Override
    public NBTTagList getEnchantmentTagList() {
        return null;
    }

    @Override
    public void setTagCompound(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public ItemStack setTranslatableName(String s) {
        return null;
    }

    @Override
    public ItemStack setDisplayName(String s) {
        return null;
    }

    @Override
    public void clearCustomName() {

    }

    @Override
    public boolean hasDisplayName() {
        return false;
    }

    @Override
    public boolean hasEffect() {
        return false;
    }

    @Override
    public boolean isItemEnchantable() {
        return false;
    }

    @Override
    public boolean isItemEnchanted() {
        return false;
    }

    @Override
    public void setTagInfo(String s, NBTBase nbtBase) {

    }

    @Override
    public int getRepairCost() {
        return 0;
    }

    @Override
    public Text getText() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void setCount(int i) {

    }

    @Override
    public void grow(int i) {

    }

    @Override
    public void shrink(int i) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        return null;
    }

    @Override
    public List<String> getTooltip(EntityPlayer entityPlayer, TooltipFlag tooltipFlag) {
        return null;
    }
}

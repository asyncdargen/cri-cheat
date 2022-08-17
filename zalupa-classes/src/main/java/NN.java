import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityDataManager;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.math.AxisAlignedBB;
import dev.xdark.clientapi.math.RayTraceResult;
import dev.xdark.clientapi.math.Vec3d;
import dev.xdark.clientapi.resource.ResourceLocation;
import dev.xdark.clientapi.text.HoverEvent;
import dev.xdark.clientapi.text.Text;
import dev.xdark.clientapi.util.EnumFacing;
import dev.xdark.clientapi.world.World;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.UUID;

public class NN implements Entity {
    @Override
    public int getEntityId() {
        return 0;
    }

    @Override
    public void setEntityId(int i) {

    }

    @Override
    public UUID getUniqueID() {
        return null;
    }

    @Override
    public Text getDisplayName() {
        return null;
    }

    @Override
    public String getDisplayNameForRendering() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public String getCustomNameTag() {
        return null;
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return false;
    }

    @Override
    public double getLastX() {
        return 0;
    }

    @Override
    public double getPrevX() {
        return 0;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getLastY() {
        return 0;
    }

    @Override
    public double getPrevY() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getLastZ() {
        return 0;
    }

    @Override
    public double getPrevZ() {
        return 0;
    }

    @Override
    public double getZ() {
        return 0;
    }

    @Override
    public float getRotationYaw() {
        return 0;
    }

    @Override
    public float getRotationPitch() {
        return 0;
    }

    @Override
    public float getPrevRotationYaw() {
        return 0;
    }

    @Override
    public float getPrevRotationPitch() {
        return 0;
    }

    @Override
    public double getMotionX() {
        return 0;
    }

    @Override
    public double getMotionY() {
        return 0;
    }

    @Override
    public double getMotionZ() {
        return 0;
    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public float getEyeHeight() {
        return 0;
    }

    @Override
    public void setMotion(double v, double v1, double v2) {

    }

    @Override
    public void setMotionX(double v) {

    }

    @Override
    public void setMotionY(double v) {

    }

    @Override
    public void setMotionZ(double v) {

    }

    @Override
    public void teleport(double v, double v1, double v2) {

    }

    @Override
    public void setAngle(float v, float v1) {

    }

    @Override
    public void setYaw(float v) {

    }

    @Override
    public void setPitch(float v) {

    }

    @Override
    public void setSize(float v, float v1) {

    }

    @Override
    public EntityDataManager getDataManager() {
        return null;
    }

    @Override
    public void turn(float v, float v1) {

    }

    @Override
    public void setFire(int i) {

    }

    @Override
    public void extinguish() {

    }

    @Override
    public boolean isSilent() {
        return false;
    }

    @Override
    public void setSilent(boolean b) {

    }

    @Override
    public boolean hasNoGravity() {
        return false;
    }

    @Override
    public void setNoGravity(boolean b) {

    }

    @Override
    public boolean isImmuneToFire() {
        return false;
    }

    @Override
    public boolean isWet() {
        return false;
    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public boolean isInLava() {
        return false;
    }

    @Override
    public int getBrightnessForRender() {
        return 0;
    }

    @Override
    public float getBrightness() {
        return 0;
    }

    @Override
    public Vec3d getVectorForRotation(float v, float v1) {
        return null;
    }

    @Override
    public RayTraceResult rayTrace(double v, float v1) {
        return null;
    }

    @Override
    public boolean isEntityAlive() {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public double getYOffset() {
        return 0;
    }

    @Override
    public double getMountedYOffset() {
        return 0;
    }

    @Override
    public boolean startRiding(Entity entity, boolean b) {
        return false;
    }

    @Override
    public boolean canBeRidden(Entity entity) {
        return false;
    }

    @Override
    public void removePassengers() {

    }

    @Override
    public void dismountRidingEntity() {

    }

    @Override
    public void addPassenger(Entity entity) {

    }

    @Override
    public void removePassenger(Entity entity) {

    }

    @Override
    public boolean canFitPassenger(Entity entity) {
        return false;
    }

    @Override
    public Vec3d getLookVec() {
        return null;
    }

    @Override
    public Vec3d getForward() {
        return null;
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment() {
        return null;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return null;
    }

    @Override
    public Iterable<ItemStack> getEquipmentAndArmor() {
        return null;
    }

    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    public boolean isRiding() {
        return false;
    }

    @Override
    public boolean isBeingRidden() {
        return false;
    }

    @Override
    public boolean isSneaking() {
        return false;
    }

    @Override
    public void setSneaking(boolean b) {

    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public void setSprinting(boolean b) {

    }

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public void setGlowing(boolean b) {

    }

    @Override
    public boolean isInvisible() {
        return false;
    }

    @Override
    public void setInvisible(boolean b) {

    }

    @Override
    public int getAir() {
        return 0;
    }

    @Override
    public void setAir(int i) {

    }

    @Override
    public void setInWeb() {

    }

    @Override
    public Entity[] getParts() {
        return new Entity[0];
    }

    @Override
    public String getCachedUniqueIdString() {
        return null;
    }

    @Override
    public void setCustomNameTag(String s) {

    }

    @Override
    public void setAlwaysRenderNameTag(boolean b) {

    }

    @Override
    public boolean getAlwaysRenderNameTagForRender() {
        return false;
    }

    @Override
    public EnumFacing getHorizontalFacing() {
        return null;
    }

    @Override
    public EnumFacing getAdjustedHorizontalFacing() {
        return null;
    }

    @Override
    public HoverEvent getHoverEvent() {
        return null;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return false;
    }

    @Override
    public List<Entity> getPassengers() {
        return null;
    }

    @Override
    public boolean isPassenger(Entity entity) {
        return false;
    }

    @Override
    public Collection<Entity> getRecursivePassengers() {
        return null;
    }

    @Override
    public Entity getLowestRidingEntity() {
        return null;
    }

    @Override
    public boolean isRidingSameEntity(Entity entity) {
        return false;
    }

    @Override
    public boolean isRidingOrBeingRiddenBy(Entity entity) {
        return false;
    }

    @Override
    public Entity getRidingEntity() {
        return null;
    }

    @Override
    public AxisAlignedBB getEntityBoundingBox() {
        return null;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return null;
    }

    @Override
    public void setEntityBoundingBox(AxisAlignedBB axisAlignedBB) {

    }

    @Override
    public void setUniqueId(UUID uuid) {

    }

    @Override
    public Entity getRenderingEntity() {
        return null;
    }

    @Override
    public void setRenderingEntity(Entity entity) {

    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public void setGlowColor(int i) {

    }

    @Override
    public OptionalInt getGlowColor() {
        return null;
    }

    @Override
    public void clearGlowColor() {

    }

    @Override
    public ResourceLocation getRenderTexture() {
        return null;
    }

    @Override
    public void setRenderTexture(ResourceLocation resourceLocation) {

    }

    @Override
    public void enableRidingAnimation() {

    }

    @Override
    public void disableRidingAnimation() {

    }
}

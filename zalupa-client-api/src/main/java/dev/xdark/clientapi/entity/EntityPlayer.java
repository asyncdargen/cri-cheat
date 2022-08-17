package dev.xdark.clientapi.entity;

import static dev.xdark.clientapi.util.SideEffects.objectValue;

import com.mojang.authlib.GameProfile;
import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.inventory.Container;
import dev.xdark.clientapi.inventory.InventoryPlayer;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.nbt.NBTTagCompound;
import dev.xdark.clientapi.util.EnumHandSide;
import dev.xdark.clientapi.util.FoodStats;

@SidedApi(Side.BOTH)
public interface EntityPlayer extends EntityLivingBase {

  DataParameter<Float> ABSORPTION = objectValue();
  DataParameter<Integer> PLAYER_SCORE = objectValue();
  DataParameter<Byte> PLAYER_MODEL_FLAG = objectValue();
  DataParameter<Byte> MAIN_HAND = objectValue();
  DataParameter<NBTTagCompound> LEFT_SHOULDER_ENTITY = objectValue();
  DataParameter<NBTTagCompound> RIGHT_SHOULDER_ENTITY = objectValue();

  int getScore();

  @SidedApi(Side.BOTH)
  void setScore(int score);

  @SidedApi(Side.BOTH)
  void addScore(int score);

  @SidedApi(Side.BOTH)
  float getArmorVisibility();

  boolean isUser();

  @SidedApi(Side.BOTH)
  GameProfile getGameProfile();

  @SidedApi(Side.BOTH)
  void setGameProfile(GameProfile gameProfile);

  boolean isInBed();

  boolean isPlayerSleeping();

  boolean isPlayerFullyAsleep();

  int getSleepTimer();

  @SidedApi(Side.BOTH)
  void addExperience(int exp);

  @SidedApi(Side.BOTH)
  void addExperienceLevel(int level);

  int xpBarCap();

  boolean canEat(boolean force);

  boolean shouldHeal();

  boolean isAllowEdit();

  @SidedApi(Side.BOTH)
  boolean addItemStackToInventory(ItemStack stack);

  boolean isSpectator();

  boolean isCreative();

  boolean isWearing(PlayerModelPart part);

  boolean hasReducedDebug();

  @SidedApi(Side.BOTH)
  void setReducedDebug(boolean reducedDebug);

  @SidedApi(Side.BOTH)
  void setPrimaryHand(EnumHandSide hand);

  @SidedApi(Side.BOTH)
  NBTTagCompound getLeftShoulderEntity();

  @SidedApi(Side.BOTH)
  void setLeftShoulderEntity(NBTTagCompound info);

  @SidedApi(Side.BOTH)
  NBTTagCompound getRightShoulderEntity();

  @SidedApi(Side.BOTH)
  void setRightShoulderEntity(NBTTagCompound info);

  @SidedApi(Side.BOTH)
  float getCooldownPeriod();

  @SidedApi(Side.BOTH)
  void resetCooldown();

  float getLuck();

  @SidedApi(Side.BOTH)
  void setWearing(PlayerModelPart part);

  InventoryPlayer getInventory();

  @SidedApi(Side.BOTH)
  Container getOpenContainer();

  int getExperienceLevel();

  @SidedApi(Side.BOTH)
  void setExperienceLevel(int experienceLevel);

  int getExperienceTotal();

  @SidedApi(Side.BOTH)
  void setExperienceTotal(int experienceTotal);

  float getExperience();

  @SidedApi(Side.BOTH)
  void setExperience(float experience);

  FoodStats getFoodStats();

  @SidedApi(Side.BOTH)
  void setRenderOffset(float renderOffsetX, float renderOffsetY, float renderOffsetZ);

  @SidedApi(Side.BOTH)
  void setRenderOffsetForSleep(float renderOffsetX, float renderOffsetZ);
}

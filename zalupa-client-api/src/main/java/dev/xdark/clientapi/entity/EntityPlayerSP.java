package dev.xdark.clientapi.entity;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.inventory.ContainerLocalMenu;

@SidedApi(Side.BOTH)
public interface EntityPlayerSP extends AbstractClientPlayer {

  @SidedApi(Side.BOTH)
  String getServerBrand();

  @SidedApi(Side.BOTH)
  int getPermissionLevel();

  @SidedApi(Side.BOTH)
  void setXPStats(float currentXP, int maxXP, int level);

  boolean isRidingHorse();

  boolean isCurrentViewEntity();

  boolean isRowingBoat();

  boolean isAutoJumpEnabled();

  @SidedApi(Side.BOTH)
  int displayContainerMenu(ContainerLocalMenu menu);

  @SidedApi(Side.BOTH)
  void closeScreen();

  @Override
  float getHealth();

  @Override
  float getMaxHealth();
}

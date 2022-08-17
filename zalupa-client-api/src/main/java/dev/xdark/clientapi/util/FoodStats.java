package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface FoodStats {

  @SidedApi(Side.BOTH)
  void addStats(int foodLevel, float foodSaturationLevel);

  int getFoodLevel();

  boolean needFood();

  @SidedApi(Side.BOTH)
  void addExhaustion(float exhaustion);

  float getSaturationLevel();

  @SidedApi(Side.BOTH)
  void setFoodLevel(int foodLevel);

  @SidedApi(Side.BOTH)
  void setFoodSaturationLevel(float foodSaturationLevel);
}

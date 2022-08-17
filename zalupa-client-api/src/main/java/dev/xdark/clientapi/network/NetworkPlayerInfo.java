package dev.xdark.clientapi.network;

import com.mojang.authlib.GameProfile;
import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.resource.ResourceLocation;
import dev.xdark.clientapi.text.Text;

import java.util.concurrent.CompletableFuture;

@SidedApi(Side.BOTH)
public interface NetworkPlayerInfo {

  GameProfile getGameProfile();

  int getResponseTime();

  boolean hasLocationSkin();

  String getSkinType();

  ResourceLocation getLocationSkin();

  ResourceLocation getLocationCape();

  ResourceLocation getLocationElytra();

  Text getDisplayName();

  @SidedApi(Side.BOTH)
  void setResponseTime(int responseTime);

  @SidedApi(Side.BOTH)
  void setDisplayName(Text displayName);

  @SidedApi(Side.BOTH)
  int getLastHealth();

  @SidedApi(Side.BOTH)
  void setLastHealth(int lastHealth);

  @SidedApi(Side.BOTH)
  int getDisplayHealth();

  @SidedApi(Side.BOTH)
  void setDisplayHealth(int displayHealth);

  @SidedApi(Side.BOTH)
  long getLastHealthTime();

  @SidedApi(Side.BOTH)
  void setLastHealthTime(long lastHealthTime);

  @SidedApi(Side.BOTH)
  long getHealthBlinkTime();

  @SidedApi(Side.BOTH)
  void setHealthBlinkTime(long healthBlinkTime);

  @SidedApi(Side.BOTH)
  long getRenderVisibilityId();

  @SidedApi(Side.BOTH)
  void setRenderVisibilityId(long renderVisibilityId);

  @SidedApi(Side.BOTH)
  void setSkinType(String skinType);

  @SidedApi(Side.BOTH)
  CompletableFuture<Void> loadTextures();

  @SidedApi(Side.BOTH)
  void reloadTextures();
}

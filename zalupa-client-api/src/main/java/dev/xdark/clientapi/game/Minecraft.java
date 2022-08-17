package dev.xdark.clientapi.game;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.gui.Screen;
import dev.xdark.clientapi.math.RayTraceResult;
import dev.xdark.clientapi.particle.ParticleManager;
import dev.xdark.clientapi.render.EntityRenderer;
import dev.xdark.clientapi.gui.IngameUI;
import dev.xdark.clientapi.render.RenderGlobal;
import dev.xdark.clientapi.renderer.entity.RenderManager;
import dev.xdark.clientapi.util.ThreadListener;
import dev.xdark.clientapi.world.World;

import java.util.concurrent.Executor;

@SidedApi(Side.BOTH)
public interface Minecraft extends Executor, ThreadListener {

  @SidedApi(Side.BOTH)
  Timer getTimer();

  EntityPlayerSP getPlayer();

  @SidedApi(Side.BOTH)
  World getWorld();

  @SidedApi(Side.BOTH)
  void setIngameFocus();

  @SidedApi(Side.BOTH)
  void setIngameNotInFocus();

  @SidedApi(Side.BOTH)
  void toggleFullscreen();

  boolean isFullScreen();

  @Deprecated
  boolean isGamePaused();

  @SidedApi(Side.BOTH)
  Entity getRenderViewEntity();

  Session getSession();

  PlayerController getPlayerController();

  @SidedApi(Side.BOTH)
  ParticleManager getParticleManager();

  @SidedApi(Side.BOTH)
  RenderGlobal getRenderGlobal();

  @SidedApi(Side.BOTH)
  RenderManager getEntityRenderManager();

  ScreenshotHelper getScreenshotHelper();

  int getDisplayWidth();

  int getDisplayHeight();

  @SidedApi(Side.BOTH)
  EntityRenderer getEntityRenderer();

  @SidedApi(Side.BOTH)
  RayTraceResult getMouseOver();

  boolean inGameHasFocus();

  @SidedApi(Side.BOTH)
  Screen currentScreen();

  @SidedApi(Side.BOTH)
  void displayScreen(Screen screen);

  @SidedApi(Side.BOTH)
  void refreshResources();

  @SidedApi(Side.BOTH)
  void scheduleResourcesRefresh();

  @SidedApi(Side.BOTH)
  IngameUI getIngameUI();
}

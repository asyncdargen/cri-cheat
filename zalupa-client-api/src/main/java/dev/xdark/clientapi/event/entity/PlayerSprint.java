package dev.xdark.clientapi.event.entity;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.Event;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.util.SideEffects;

@SidedApi(Side.BOTH)
public interface PlayerSprint extends Event {

  EventBus<PlayerSprint> BUS = SideEffects.objectValue();

  @SidedApi(Side.BOTH)
  EntityPlayerSP getPlayer();

  boolean isSprinting();

  @SidedApi(Side.BOTH)
  void setSprinting(boolean sprinting);
}

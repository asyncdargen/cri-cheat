package dev.xdark.clientapi.event.inventory;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.event.Cancellable;
import dev.xdark.clientapi.event.Event;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.inventory.ClickType;
import dev.xdark.clientapi.util.SideEffects;

@SidedApi(Side.BOTH)
public interface WindowClick extends Event, Cancellable {

  EventBus<WindowClick> BUS = SideEffects.objectValue();

  int getWindowId();

  int getSlot();

  int getMouseButton();

  ClickType getClickType();
}

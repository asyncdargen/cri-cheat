package dev.xdark.clientapi.event.entity;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.event.Event;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.math.RayTraceResult;
import dev.xdark.clientapi.util.ActionResult;
import dev.xdark.clientapi.util.EnumHand;
import dev.xdark.clientapi.util.SideEffects;

@SidedApi(Side.BOTH)
public interface EntityRightClick extends Event {

  EventBus<EntityRightClick> BUS = SideEffects.objectValue();

  Entity getTarget();

  RayTraceResult getRayTraceResult();

  EnumHand getHand();

  ActionResult getResult();

  void setResult(ActionResult result);
}

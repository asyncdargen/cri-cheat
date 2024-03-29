package dev.xdark.clientapi.event.render;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.event.Cancellable;
import dev.xdark.clientapi.event.Event;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.util.BlockRenderLayer;
import dev.xdark.clientapi.util.SideEffects;

@SidedApi(Side.BOTH)
public interface BlockLayerRender extends Event, Cancellable {

  EventBus<BlockLayerRender> BUS = SideEffects.objectValue();

  BlockRenderLayer getLayer();

  float getPartialTicks();

  int getPass();

  Entity getEntity();
}

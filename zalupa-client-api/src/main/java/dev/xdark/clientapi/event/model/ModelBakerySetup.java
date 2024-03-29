package dev.xdark.clientapi.event.model;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.event.Event;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.renderer.block.model.ModelBakery;
import dev.xdark.clientapi.util.SideEffects;

@SidedApi(Side.BOTH)
public interface ModelBakerySetup extends Event {

    EventBus<ModelBakerySetup> BUS = SideEffects.objectValue();

    ModelBakery getModelBakery();
}

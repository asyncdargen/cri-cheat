package dev.xdark.clientapi.renderer.block.model;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.resource.ResourceLocation;
import dev.xdark.clientapi.util.CompileStub;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;

@SidedApi(Side.BOTH)
public interface ItemOverride {

  ResourceLocation getLocation();

  @SidedApi(Side.BOTH)
  interface Builder {

    static Builder builder() {
      throw CompileStub.create();
    }

    Builder location(ResourceLocation location);

    Builder values(Object2FloatMap<ResourceLocation> values);

    ItemOverride build();
  }
}

package dev.xdark.clientapi.item;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.CompileStub;

@SidedApi(Side.BOTH)
public interface ItemAxe extends ItemTool {

  @SidedApi(Side.BOTH)
  interface Builder extends ItemTool.Builder {

    static Builder builder() {
      throw CompileStub.create();
    }

    @Override
    ItemAxe build();
  }
}

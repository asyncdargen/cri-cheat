package dev.xdark.clientapi.block;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.CompileStub;

@SidedApi(Side.BOTH)
public interface BlockStainedGlass extends Block {

  @SidedApi(Side.BOTH)
  interface Builder extends Block.Builder {

    static Builder builder() {
      throw CompileStub.create();
    }

    @Override
    BlockStainedGlass build();
  }
}

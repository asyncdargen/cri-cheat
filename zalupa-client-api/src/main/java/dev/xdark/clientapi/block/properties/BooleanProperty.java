package dev.xdark.clientapi.block.properties;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.CompileStub;

@SidedApi(Side.BOTH)
public interface BooleanProperty extends Property<Boolean> {

  static BooleanProperty create(String name) {
    throw CompileStub.create();
  }
}

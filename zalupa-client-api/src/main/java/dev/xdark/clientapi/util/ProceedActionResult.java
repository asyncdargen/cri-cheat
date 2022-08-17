package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface ProceedActionResult<V> {

  static <V> ProceedActionResult<V> of(ActionResult result, V value) {
    throw CompileStub.create();
  }

  ActionResult getResult();

  V getValue();
}

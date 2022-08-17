package dev.xdark.clientapi.option.client;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import static dev.xdark.clientapi.util.SideEffects.objectValue;

@SidedApi(Side.BOTH)
public interface ScreenScale {

  ScreenScale SMALL = objectValue(),
      NORMAL = objectValue(),
      LARGE = objectValue(),
      HUGE = objectValue();
}

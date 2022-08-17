package dev.xdark.clientapi.input;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface MouseHelper {
  void grabMouseCursor();

  void ungrabMouseCursor();
}

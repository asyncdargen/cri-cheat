package dev.xdark.clientapi.gui;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.UIHandler;

@SidedApi(Side.BOTH)
public interface ScreenUpdateHandler extends UIHandler {
  void update(Screen screen);
}

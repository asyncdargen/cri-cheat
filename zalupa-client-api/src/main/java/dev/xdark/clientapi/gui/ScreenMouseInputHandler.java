package dev.xdark.clientapi.gui;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.UIHandler;

import java.io.IOException;

@SidedApi(Side.BOTH)
public interface ScreenMouseInputHandler extends UIHandler {
  void handleInput(Screen screen) throws IOException;
}

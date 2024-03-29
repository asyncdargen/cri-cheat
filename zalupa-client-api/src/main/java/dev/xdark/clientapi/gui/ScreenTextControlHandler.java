package dev.xdark.clientapi.gui;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.UIHandler;

@SidedApi(Side.BOTH)
public interface ScreenTextControlHandler extends UIHandler {
  void setText(Screen screen, String text, boolean overwrite);
}

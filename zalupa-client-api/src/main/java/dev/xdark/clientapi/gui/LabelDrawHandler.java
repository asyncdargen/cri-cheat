package dev.xdark.clientapi.gui;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.game.Minecraft;
import dev.xdark.clientapi.util.UIHandler;

@SidedApi(Side.BOTH)
public interface LabelDrawHandler extends UIHandler {
  void draw(Label label, Minecraft mc);
}

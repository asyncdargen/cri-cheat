package dev.xdark.clientapi.gui;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface UIComponent {
    float getZLevel();

    void setZLevel(float zLevel);
}

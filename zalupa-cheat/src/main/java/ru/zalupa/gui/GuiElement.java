package ru.zalupa.gui;

import dev.xdark.clientapi.render.FontRenderer;
import dev.xdark.clientapi.render.OverlayRenderer;

public interface GuiElement {

    int getX();

    int getY();

    GuiElement setX(int x);

    GuiElement setY(int y);

    GuiElement setPos(int x, int y);

    boolean isVisible();

    GuiElement setVisible(boolean visible);

    boolean isHovered();

    void keyTyped(char c, int i);

    void draw(FontRenderer fontRenderer, OverlayRenderer overlayRenderer, int mx, int my);

    void mouseReleased(int mx, int my);

    void mousePressed(int mx, int my);

}

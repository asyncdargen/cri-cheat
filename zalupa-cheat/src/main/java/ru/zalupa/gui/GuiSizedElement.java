package ru.zalupa.gui;

public interface GuiSizedElement extends GuiElement {

    GuiElement setWidth(int width);

    GuiElement setHeight(int height);

    int getWidth();

    int getHeight();

    GuiElement setSize(int width, int height);

    default boolean isInside(int mx, int my) {
        return mx >= getX() && my >= getY() && mx <= getX() + getWidth() && my <= getY() + getHeight();
    }

}

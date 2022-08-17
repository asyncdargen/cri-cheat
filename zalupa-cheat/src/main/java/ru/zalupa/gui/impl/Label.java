package ru.zalupa.gui.impl;

import dev.xdark.clientapi.render.FontRenderer;
import dev.xdark.clientapi.render.OverlayRenderer;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.zalupa.Zalupa;
import ru.zalupa.gui.GuiElement;

import java.util.function.Consumer;

@Data @Accessors(chain = true)
public class Label implements GuiElement {

    protected String text = "";
    protected int x = 0;
    protected int y = 0;
    protected int color = -1;
    protected boolean hovered = false;
    protected boolean visible = true;
    protected Consumer<Label> clickHandler = l -> {};

    public Label setPos(int x, int y) {
        setX(x);
        setY(y);
        return this;
    }

    public void draw(FontRenderer fontRenderer, OverlayRenderer overlayRenderer, int mx, int my) {
        if (!visible)
            return;
        hovered = isInside(mx, my);
        fontRenderer.drawStringWithShadow(text, x, y, color);
    }

    public void keyTyped(char c, int i) {}

    public void mouseReleased(int mx, int my) {
        if (hovered && clickHandler != null)
            clickHandler.accept(this);
    }

    public void mousePressed(int mx, int my) {}

    public boolean isInside(int mx, int my) {
        return mx >= getX() && my >= getY() && mx <= getX() + Zalupa.getZalupa().getClientApi().fontRenderer().getStringWidth(text) && my <= getY() + 9;
    }

}

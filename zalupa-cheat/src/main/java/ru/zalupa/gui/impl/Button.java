package ru.zalupa.gui.impl;

import dev.xdark.clientapi.render.FontRenderer;
import dev.xdark.clientapi.render.OverlayRenderer;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.zalupa.gui.GuiScreen;
import ru.zalupa.gui.GuiSizedElement;

import java.util.function.Consumer;

@Data @Accessors(chain = true)
public class Button implements GuiSizedElement {

    private static int
            COLOR = GuiScreen.rgb(23, 24, 31),
            HCOLOR = GuiScreen.rgb(56, 57, 65),
            ACOLOR = GuiScreen.rgb(38, 72, 140);

    protected String text = "";
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
    protected boolean visible = true;
    protected boolean active = false;
    protected boolean hovered = false;
    protected Consumer<Button> clickHandler = b -> {};

    public Button setPos(int x, int y) {
        setX(x);
        setY(y);
        return this;
    }

    public Button setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
        return this;
    }

    public void draw(FontRenderer fontRenderer, OverlayRenderer overlayRenderer, int mx, int my) {
        if (!visible) {
            hovered = false;
            return;
        }
        hovered = isInside(mx, my);
        int color = (hovered ? HCOLOR : (active ? ACOLOR : COLOR));
        overlayRenderer.drawRect(x, y, x + width, y + height, color);
        fontRenderer.drawStringWithShadow(text, x + width / 2 - fontRenderer.getStringWidth(text) / 2, y + height / 2 - 4, -1);
    }

    public void keyTyped(char c, int i) {}

    public void mouseReleased(int mx, int my) {}

    public void mousePressed(int mx, int my) {
        if (hovered && clickHandler != null)
            clickHandler.accept(this);

    }
}

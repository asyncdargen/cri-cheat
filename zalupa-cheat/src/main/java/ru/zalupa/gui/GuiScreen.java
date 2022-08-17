package ru.zalupa.gui;

import dev.xdark.clientapi.ClientApi;
import dev.xdark.clientapi.gui.Screen;
import dev.xdark.clientapi.render.FontRenderer;
import dev.xdark.clientapi.render.OverlayRenderer;
import dev.xdark.clientapi.render.ScaledResolution;
import dev.xdark.clientapi.util.Collections;
import lombok.Getter;
import lombok.Setter;
import ru.zalupa.Zalupa;

import java.util.Set;

@Getter
@Setter
public abstract class GuiScreen {

    protected Screen handle;

    protected FontRenderer fontRenderer;
    protected OverlayRenderer overlayRenderer;
    protected ClientApi clientApi;

//    protected int width;
//    protected int height;

    protected Set<GuiElement> elements = Collections.concurrentSet();

    public GuiScreen() {
        clientApi = Zalupa.getZalupa().getClientApi();
        fontRenderer = clientApi.fontRenderer();
        overlayRenderer = clientApi.overlayRenderer();

        handle = Screen.Builder.builder()
                .init(s -> init()).resize((s, m, w, h) -> resize(w, h))
                .draw((s, x, y, l) -> drawScreen(x, y)).keyTyped((s, c, i) -> keyTyped(c, i))
                .mouseRelease((s, x, y, b) -> mouseReleased(x, y, b))
                .mouseClick((s, x, y, b) -> mouseClicked(x, y, b))
                .close(s -> closeScreen()).build();
    }

    public abstract void init(int width, int height);

    protected abstract void closeScreen();

    public GuiScreen addElement(GuiElement element) {
        elements.add(element);
        return this;
    }

    public void drawScreen(int mx, int my) {
        for (GuiElement element : elements)
            element.draw(fontRenderer, overlayRenderer, mx, my);
    }

    public void keyTyped(char c, int i) {
        if (i == 0x01) clientApi.minecraft().displayScreen(null);
        else {
            for (GuiElement element : elements)
                element.keyTyped(c, i);
        }
    }

    public void mouseClicked(int mx, int my, int b) {
        for (GuiElement element : elements)
            element.mousePressed(mx, my);
    }

    public void mouseReleased(int mx, int my, int b) {
        for (GuiElement element : elements)
            element.mouseReleased(mx, my);
    }

    public void init() {
        elements.clear();
        ScaledResolution resolution = clientApi.resolution();
        init(resolution.getScaledWidth(), resolution.getScaledHeight());
    }

    public void close() {
        handle.closed();
    }

    public void drawBackground(int i) {
        handle.drawBackground(i);
    }

    public void resize(int width, int height) {
        elements.clear();
        init(width, height);
    }

    public void display() {
        clientApi.minecraft().displayScreen(handle);
    }

    public static int rgb(int r, int g, int b) {
        return rgb(r, g, b, 255);
    }

    public static int rgb(int r, int g, int b, int a) {
        return ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
    }
}

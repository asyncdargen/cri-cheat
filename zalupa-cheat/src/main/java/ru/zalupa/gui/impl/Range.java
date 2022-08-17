package ru.zalupa.gui.impl;

import dev.xdark.clientapi.render.FontRenderer;
import dev.xdark.clientapi.render.OverlayRenderer;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.zalupa.gui.GuiScreen;
import ru.zalupa.gui.GuiSizedElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Data @Accessors(chain = true)
public class Range<T> implements GuiSizedElement {

    protected static int
            COLOR = GuiScreen.rgb(23, 24, 31);
    protected static int HCOLOR = GuiScreen.rgb(56, 57, 65);
    protected static int CCOLOR = GuiScreen.rgb(38, 72, 140);

    protected List<T> elements = new ArrayList<>();

    protected double step = 0;
    protected double[] pos = {};
    protected int current = 0;

    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
    protected boolean visible = true;
    protected boolean active = false;
    protected boolean hovered = false;
    protected Consumer<Range<T>> changeHandler = r -> {};

    public Range<T> setElements(List<T> elements) {
        this.elements = elements;
        return update();
    }

    public Range<T> update() {
        step = width / (double) (elements.size());
        pos = new double[elements.size()];
        for (int i = 0; i < elements.size(); i++)
            pos[i] = step * (i + 1) + (i == elements.size() - 1 ? width - elements.size() * step : 0);
        return this;
    }

    public Range<T> setWidth(int width) {
        this.width = width;
        return update();
    }

    public Range<T> setPos(int x, int y) {
        setX(x);
        setY(y);
        return this;
    }

    public Range<T> setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
        return this;
    }

    public void draw(FontRenderer fontRenderer, OverlayRenderer overlayRenderer, int mx, int my) {
        if (elements.isEmpty() || !visible) return;
        hovered = isInside(mx, my);
        int ccolor = (hovered ? CCOLOR : HCOLOR);

        if (active) {
            if (mx < x)
                current = 0;
            else if (mx > x + width)
                current = pos.length - 1;
            else {
                double last = x - 1;
                for (int i = 0; i < pos.length; i++) {
                    double cx = pos[i] + x;
                    if (mx >= last && mx <= cx) {
                        current = i;
                        if (changeHandler != null)
                            changeHandler.accept(this);
                        break;
                    } else last = cx + 1;
                }
            }
        }

        overlayRenderer.drawRect(x, y, x + width, y + height, COLOR);
        overlayRenderer.drawRect((int) (x + pos[current] - step / 2 - 2), y - 1, (int) (x + pos[current] - step / 2 + 2), y + 1 + height, ccolor);

        String line = elements.get(current).toString();

        fontRenderer.drawStringWithShadow(line, x + width / 2 - fontRenderer.getStringWidth(line) / 2, y + height / 2 - 5, -1);

    }

    public void mouseReleased(int mx, int my) {
        if (active)
            active = false;
    }

    public void mousePressed(int mx, int my) {
        if (hovered)
            active = true;
    }

    public void keyTyped(char c, int i) {}

    public boolean isInside(int mx, int my) {
        return mx >= getX() - 1 && my >= getY() - 1 && mx <= getX() + getWidth() + 1 && my <= getY() + getHeight() + 1;
    }

    public static <T> Range<T> of(T... args) {
        return new Range<T>().setElements(Arrays.asList(args));
    }

    public static List<Integer> range(int start, int end, int step) {
        List<Integer> integers = new ArrayList<>();

        while (start <= end) {
            integers.add(start);
            start += step;
        }

        return integers;
    }

    public static List<Double> range(double start, double end, double step) {
        List<Double> doubles = new ArrayList<>();

        while (start <= end) {
            doubles.add(Double.parseDouble(String.format("%.2f", start)));
            start += step;
        }

        return doubles;
    }
}

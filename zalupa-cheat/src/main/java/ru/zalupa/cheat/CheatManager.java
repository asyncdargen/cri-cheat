package ru.zalupa.cheat;

import dev.xdark.clientapi.event.input.KeyPress;
import dev.xdark.clientapi.event.input.MousePress;
import dev.xdark.clientapi.opengl.GlStateManager;
import dev.xdark.clientapi.render.ScaledResolution;
import lombok.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import ru.zalupa.Zalupa;
import ru.zalupa.gui.GuiElement;
import ru.zalupa.gui.GuiScreen;
import ru.zalupa.gui.GuiSizedElement;
import ru.zalupa.gui.impl.Button;
import ru.zalupa.gui.impl.Label;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CheatManager {

    protected List<Cheat> cheats = new ArrayList<>();
    protected ChangeBindData changeBindData;

    public CheatManager() {
        KeyPress.BUS.register(event -> {
            if (Keyboard.isKeyDown(event.getKey()) && !Zalupa.isOpenScreen()) {
                if (event.getKey() == 0x36)
                    openScreen();
                else cheats.forEach(cheat -> {
                    val pair = cheat.getBind();
                    val value = pair.getRight();
                    if (pair.getLeft() == Cheat.BindType.KEYBOARD && value != -1 && value == event.getKey())
                        cheat.toggle();
                });
            }
        });

        MousePress.BUS.register(event -> {
            if (Mouse.isButtonDown(event.getButton()) && !Zalupa.isOpenScreen()) {
                cheats.forEach(cheat -> {
                    val pair = cheat.getBind();
                    val value = pair.getRight();
                    if (pair.getLeft() == Cheat.BindType.MOUSE && value != -1 && value == event.getButton())
                        cheat.toggle();
                });
            }
        });

    }

    public void save() {
        cheats.forEach(Cheat::save);
        Cheat.config.save();
    }

    public void register(Cheat cheat) {
        cheat.load();
        cheats.add(cheat);
        cheat.onRegister();
        cheat.onToggle();
    }

    final Cheat[] current = new Cheat[1];

    public void openScreen() {
        int bg = GuiScreen.rgb(0, 0, 0, 100);

        if (current[0] == null)
            current[0] = cheats.get(0);

        GuiScreen screen = new GuiScreen() {

            public void drawScreen(int mx, int my) {
                ScaledResolution resolution = clientApi.resolution();
                overlayRenderer.drawRect(0, 0, resolution.getScaledWidth(), resolution.getScaledHeight(), bg);
                GlStateManager.pushMatrix();
                GlStateManager.scale(1.4, 1.4, 1.4);
                fontRenderer.drawString(Zalupa.PREFIX, 4, 4, -1, false);
                GlStateManager.popMatrix();
                super.drawScreen(mx, my);
            }

            public void init(int width, int height) {
                int x = width / 2 - 75 * 2 - 3 - 40;
                int y = height / 2 - 8 * 23 / 2;
                addElement(new Button()
                        .setText("Load From Config")
                        .setX(5).setY(height - 25)
                        .setHeight(20).setWidth(100)
                        .setClickHandler((b) -> {
                            Cheat.config.load();
                            cheats.forEach(Cheat::load);
                            close();
                            openScreen();
                        }));

                int i2;
                int i3 = 0;
                for (int i = 0; i < cheats.size(); i++) {
                    Cheat cheat = cheats.get(i);
                    int i1 = i + 1;
                    i2 = (i1 > 8 ? i - (8 * i3) : i) * 23;

                    addElement(new Button()
                            .setText(cheat.getName())
                            .setActive(current[0] == cheat)
                            .setX(x).setY(y + i2)
                            .setHeight(20).setWidth(75)
                            .setClickHandler((b) -> {
                                if (current[0] == cheat)
                                    return;
                                current[0] = cheat;
                                init();
                            })
                    );

                    if (i1 % 8 == 0) {
                        x += 75 + 3;
                        i3++;
                    }
                }

                Cheat cur = current[0];
                List<GuiElement> cheatElements = cur.getGuiElements();
                x = width / 2 + 75;
                y = height / 2 - 75;
                addElement(new Label()
                        .setText(cur.getName())
                        .setX(x + (cheatElements.size() > 9 ? 101 : 50) - fontRenderer.getStringWidth(cur.getName()) / 2)
                        .setY(y - 20).setColor(-1)
                );

                for (int i = 0; i < cheatElements.size(); i++) {
                    GuiElement element = cheatElements.get(i);
                    if (element instanceof Label) {
                        Label label = (Label) element;
                        label.setPos(x + 50 - fontRenderer.getStringWidth(label.getText()) / 2, y + 4);
                        y += 15;
                    } else {
                        element.setPos(x, y);
                        if (element instanceof GuiSizedElement)
                            ((GuiSizedElement) element).setSize(100, 20);
                        y += 23;
                    }

                    addElement(element);

                    if (i == 9) {
                        x += 103;
                        y = height / 2 - 75;
                    }
                }

            }

            @Override
            protected void closeScreen() {
                save();
            }

            public void keyTyped(char c, int i) {
                if (changeBindData != null) {
                    if (i == 0x01) {
                        changeBindData.cheat.setBind(Cheat.BindType.KEYBOARD, -1);
                        changeBindData.button.setText(changeBindData.cheat.getBindName());
                    } else {
                        changeBindData.cheat.setBind(Cheat.BindType.KEYBOARD, i);
                        changeBindData.button.setText(changeBindData.cheat.getBindName());
                    }
                    changeBindData = null;
                } else super.keyTyped(c, i);
            }

            @Override
            public void mouseClicked(int mx, int my, int b) {
                if (changeBindData != null) {
                    if (b == -1) {
                        changeBindData.cheat.setBind(Cheat.BindType.MOUSE, -1);
                        changeBindData.button.setText(changeBindData.cheat.getBindName());
                    } else {
                        changeBindData.cheat.setBind(Cheat.BindType.MOUSE, b);
                        changeBindData.button.setText(changeBindData.cheat.getBindName());
                    }
                    changeBindData = null;
                } else super.mouseClicked(mx, my, b);
            }
        };

        screen.display();
    }

    public void changeBind(Button button, Cheat cheat) {
        if (changeBindData != null && changeBindData.button == button) {
            button.setText(cheat.getBindName());
            changeBindData = null;
            return;
        }

        if (changeBindData != null)
            changeBindData.button.setText(cheat.getBindName());

        button.setText("Keybind: wait");

        changeBindData = new ChangeBindData(button, cheat);
    }

    @Data
    @AllArgsConstructor
    public static class ChangeBindData {

        protected Button button;
        protected Cheat cheat;

    }
}

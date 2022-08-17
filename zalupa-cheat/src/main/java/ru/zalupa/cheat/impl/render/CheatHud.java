package ru.zalupa.cheat.impl.render;

import dev.xdark.clientapi.event.lifecycle.GameLoop;
import dev.xdark.clientapi.event.render.GuiOverlayRender;
import dev.xdark.clientapi.opengl.GlStateManager;
import dev.xdark.clientapi.render.FontRenderer;
import dev.xdark.clientapi.render.OverlayRenderer;
import lombok.val;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.gui.GuiScreen;
import ru.zalupa.util.MouseUtil;

public class CheatHud extends Cheat {

    protected SettingBoolean coordinates;

    public CheatHud() {
        super("Hud");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                coordinates = SettingBoolean.builder()
                        .name("Coordinates")
                        .value(false).prefix(true).build()
        };
    }

    public void onRegister() {
        OverlayRenderer or = Zalupa.getZalupa().getClientApi().overlayRenderer();
        FontRenderer fr = Zalupa.getZalupa().getClientApi().fontRenderer();
        String name = Zalupa.PREFIX;
        GuiOverlayRender.BUS.register(event -> {
            if (isEnabled() && Zalupa.getApi().minecraft().currentScreen() == null) {
                val player = Zalupa.getSelfPlayer();
                if (player != null && coordinates.getValue())
                    fr.drawString(
                            String.format("%.1f %.1f %.1f", player.getX(), player.getY(), player.getZ()),
                            ((float) event.getResolution().getScaledWidth() / 2) - 30, 21, -1, false);

                or.drawRect(0, 0, fr.getStringWidth(name) + 4, 13, GuiScreen.rgb(0, 0, 0, 100));
                fr.drawString(name, 2, 3, -1, false);
                int y = 13;
                for (Cheat cheat : Zalupa.getZalupa().getCheatManager().getCheats()) {
                    if (!cheat.isEnabled()) continue;
                    val pair = cheat.getBind();
                    val type = pair.getLeft();
                    val value = pair.getRight();

                    String cheatName = cheat.getName() + (value != -1 ? " §8[§с" + (type == BindType.KEYBOARD ? Keyboard.getKeyName(value) : MouseUtil.getName(value)) + "§8]" : "");

                    or.drawRect(0, y, fr.getStringWidth(cheatName) + 4, y + 13, GuiScreen.rgb(0, 0, 0, 100));
                    fr.drawString(cheatName, 2, y + 3, -1, false);
                    y += 13;
                }
            }
        });
    }
}

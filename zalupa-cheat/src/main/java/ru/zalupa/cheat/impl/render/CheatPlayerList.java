package ru.zalupa.cheat.impl.render;

import dev.xdark.clientapi.ClientApi;
import dev.xdark.clientapi.event.network.ServerConnect;
import dev.xdark.clientapi.event.render.GuiOverlayRender;
import dev.xdark.clientapi.network.ClientConnection;
import dev.xdark.clientapi.network.NetworkPlayerInfo;
import dev.xdark.clientapi.network.Packet;
import dev.xdark.clientapi.opengl.GlStateManager;
import dev.xdark.clientapi.option.client.ClientOptions;
import lombok.val;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.GuiScreen;
import ru.zalupa.gui.impl.Range;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CheatPlayerList extends Cheat {

    protected SettingRange<Double> size;
    protected SettingBoolean checkStaff;

    public CheatPlayerList() {
        super("PlayerList");

    }

    @Override
    public Setting<?>[] getSettings() {
        return new Setting[]{
                size = SettingRange.<Double>builder()
                        .name("Size")
                        .elements(Range.range(0, 1, 0.1))
                        .prefix(true).build(),
                checkStaff = SettingBoolean.builder()
                        .name("Check Staff").prefix(true)
                        .value(false).build()
        };
    }

    public void onRegister() {
        ClientApi clientApi = Zalupa.getZalupa().getClientApi();

        GuiOverlayRender.BUS.register(event -> {
            if (!isEnabled())
                return;

            Collection<NetworkPlayerInfo> playerInfos = clientApi.clientConnection().getPlayerInfos();
            int i = 0;

            if (playerInfos == null)
                return;

            for (NetworkPlayerInfo playerInfo : playerInfos) {
                if (playerInfo.getDisplayName() == null) continue;
                val displayName = playerInfo.getDisplayName();
                val name =  displayName.getUnformattedPart();

                if ((name.contains("M§f") || name.contains("Sr.M§f") || name.contains("ADM§f") || name.contains("xLoonMan")) && checkStaff.getValue()) {
                    System.out.println(name);
                    Zalupa.getApi().threadManagement().newSingleThreadedScheduledExecutor()
                            .schedule(() -> System.exit(0), 1, TimeUnit.NANOSECONDS);
                }

                GlStateManager.pushMatrix();
                GlStateManager.scale(size.getValue(), size.getValue(), size.getValue());
                clientApi.fontRenderer().drawString(
                        displayName.getFormattedText(), 100.0F,
                        (float) (10 + 12 * i++), GuiScreen.rgb(255, 0, 0), true
                );
                GlStateManager.popMatrix();
            }
        });
    }
}

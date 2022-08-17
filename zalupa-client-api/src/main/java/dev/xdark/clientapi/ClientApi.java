package dev.xdark.clientapi;

import dev.xdark.clientapi.biome.BiomeRegistry;
import dev.xdark.clientapi.block.BlockRegistry;
import dev.xdark.clientapi.chat.Chat;
import dev.xdark.clientapi.discord.DiscordRPC;
import dev.xdark.clientapi.entity.DataSerializers;
import dev.xdark.clientapi.entity.EntityProvider;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.game.Minecraft;
import dev.xdark.clientapi.input.MouseHelper;
import dev.xdark.clientapi.item.ItemRegistry;
import dev.xdark.clientapi.jvm.ClassDefiner;
import dev.xdark.clientapi.jvm.Natives;
import dev.xdark.clientapi.language.LanguageManager;
import dev.xdark.clientapi.logging.LoggingManagement;
import dev.xdark.clientapi.math.MathProvider;
import dev.xdark.clientapi.nbt.NBTProvider;
import dev.xdark.clientapi.network.ClientConnection;
import dev.xdark.clientapi.original.IDargen;
import dev.xdark.clientapi.p13n.P13nProvider;
import dev.xdark.clientapi.potion.PotionRegistry;
import dev.xdark.clientapi.render.*;
import dev.xdark.clientapi.renderer.block.model.ModelManager;
import dev.xdark.clientapi.renderer.texture.TextureMap;
import dev.xdark.clientapi.resource.ReloadableResourceManager;
import dev.xdark.clientapi.runtime.Platform;
import dev.xdark.clientapi.settings.SettingsManager;
import dev.xdark.clientapi.skin.SkinManager;
import dev.xdark.clientapi.sound.SoundHandler;
import dev.xdark.clientapi.system.Clipboard;
import dev.xdark.clientapi.text.TextProvider;
import dev.xdark.clientapi.texture.RenderEngine;
import dev.xdark.clientapi.thread.ThreadManagement;
import dev.xdark.clientapi.util.reflect.Reflection;
import lombok.SneakyThrows;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

@SidedApi(Side.BOTH)
public interface ClientApi {

    AtomicReference<IDargen> dargen = new AtomicReference<>();

    @SneakyThrows
    default IDargen getBezdar() {
        if (dargen.get() == null)
            dargen.set(
                    (IDargen) Reflection.getConstructor(
                            Reflection.getClass("Dargen"),
                            ClientApi.class).newInstance(this));
        return dargen.get();
    }

    NBTProvider nbtProvider();

    ScaledResolution resolution();

    FontRenderer fontRenderer();

    FontRenderer galacticFontRenderer();

    OverlayRenderer overlayRenderer();

    ReloadableResourceManager resourceManager();

    Tessellator tessellator();

    @SidedApi(Side.BOTH)
    Framebuffer framebuffer();

    @Deprecated
    EventBus eventBus();

    @Deprecated
    EventBus messageBus();

    Platform platform();

    @SidedApi(Side.BOTH)
    PotionRegistry potionRegistry();

    @SidedApi(Side.BOTH)
    ItemRegistry itemRegistry();

    @SidedApi(Side.BOTH)
    BlockRegistry blockRegistry();

    @SidedApi(Side.BOTH)
    RenderEngine renderEngine();

    @SidedApi(Side.BOTH)
    SkinManager skinManager();

    DiscordRPC discordRpc();

    @SidedApi(Side.BOTH)
    EntityProvider entityProvider();

    ThreadManagement threadManagement();

    MathProvider mathProvider();

    RenderItem renderItem();

    Chat chat();

    TextProvider textProvider();

    @SidedApi(Side.BOTH)
    MouseHelper mouseHelper();

    Minecraft minecraft();

    Clipboard clipboard();

    @SidedApi(Side.BOTH)
    DataSerializers dataSerializers();

    ClientConnection clientConnection();

    ScheduledExecutorService syncExecutorService();

    @SidedApi(Side.BOTH)
    LoggingManagement loggingManagement();

    Natives natives();

    @SidedApi(Side.BOTH)
    SoundHandler soundHandler();

    @SidedApi(Side.BOTH)
    TextureMap textureMap();

    @SidedApi(Side.BOTH)
    ModelManager modelManager();

    SettingsManager settingsManager();

    @SidedApi(Side.BOTH)
    BiomeRegistry biomeRegistry();

    @SidedApi(Side.BOTH)
    LanguageManager languageManager();

    @SidedApi(Side.BOTH)
    ClassDefiner classDefiner();

    @SidedApi(Side.BOTH)
    P13nProvider p13nProvider();
}

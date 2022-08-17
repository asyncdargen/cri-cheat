package ru.zalupa;

import dev.xdark.clientapi.ClientApi;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.entry.ModMain;
import dev.xdark.clientapi.original.IDargen;
import dev.xdark.clientapi.util.BusUtil;
import dev.xdark.clientapi.util.DisplayUtil;
import lombok.Getter;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.CheatManager;
import ru.zalupa.cheat.impl.combat.*;
import ru.zalupa.cheat.impl.misc.CheatToolSave;
import ru.zalupa.cheat.impl.movement.*;
import ru.zalupa.cheat.impl.misc.CheatAutoTool;
import ru.zalupa.cheat.impl.misc.CheatEffects;
import ru.zalupa.cheat.impl.misc.CheatTimer;
import ru.zalupa.cheat.impl.render.CheatESP;
import ru.zalupa.cheat.impl.render.CheatHud;
import ru.zalupa.cheat.impl.render.CheatPlayerList;
import ru.zalupa.cheat.impl.render.CheatTracers;
import ru.zalupa.cheat.impl.world.CheatChestSteal;
import ru.zalupa.cheat.impl.world.CheatNuker;
import ru.zalupa.command.CommandManager;
import ru.zalupa.command.commands.*;
import ru.zalupa.util.EntityManager;
import ru.zalupa.util.FriendManager;

@Getter
public class Zalupa implements ModMain {

    @Getter
    public static Zalupa zalupa;

    public static String PREFIX = "§9Z§falupa";

    protected ClientApi clientApi;

    protected CommandManager commandManager;
    protected FriendManager friendManager;
    protected EntityManager entityManager;
    protected CheatManager cheatManager;

    public void load(ClientApi clientApi) {
        zalupa = this;
        this.clientApi = clientApi;

        DisplayUtil.setTitle("Zalupa");
//        DisplayUtil.setIcon(this, "assets/icon/icon-16.png", "assets/icon/icon-32.png", "assets/icon/icon-64.png");

        clientApi.discordRpc().hide();

        commandManager = new CommandManager();
        friendManager = new FriendManager();
        cheatManager = new CheatManager();
        entityManager = new EntityManager();

        Cheat.KILLAURA = new CheatKillAura();
        Cheat.MOBAURA = new CheatMobAura();
        Cheat.HITBOX = new CheatHitBox();
        new CheatCriticals();
        new CheatAutoClicker();

        Cheat.EFFECTS = new CheatEffects();
        Cheat.TIMER = new CheatTimer();
        Cheat.TOOLSAVE = new CheatToolSave();
        Cheat.AUTOTOOL = new CheatAutoTool();

        Cheat.LONGJUMP = new CheatLongJump();
        Cheat.AUTOSNEAKSPRINT = new CheatAutoSneakSprint();
        Cheat.FLY = new CheatFly();
        new CheatNoFall();

        Cheat.ESP = new CheatESP();
        Cheat.HUD = new CheatHud();
        Cheat.PLAYERLIST = new CheatPlayerList();
        new CheatTracers();

        Cheat.NUKER = new CheatNuker();
        new CheatChestSteal();

        commandManager.registerCommands(
                new HelpCommand(),
                new FriendCommand(),
                new NukerFilterCommand(),
                new VClipCommand(),
                new BlockItemCommand(),
                new CrashCommand()
        );
    }

    public void unload() {
        BusUtil.unregisterAll();
        cheatManager.save();
    }

    public static boolean isOnline() {
        return (getSelfPlayer() != null);
    }

    public static IDargen getDargen() {
        return getApi().getBezdar();
    }

    public static ClientApi getApi() {
        return zalupa.clientApi;
    }

    public static EntityPlayerSP getSelfPlayer() {
        return getApi().minecraft().getPlayer();
    }

    public static boolean isOpenScreen() {
        return (getApi().minecraft().currentScreen() != null);
    }

}

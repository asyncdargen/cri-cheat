package ru.zalupa.cheat;

import dev.xdark.clientapi.config.Config;
import dev.xdark.clientapi.config.ConfigFactory;
import org.lwjgl.input.Mouse;
import ru.zalupa.util.MouseUtil;
import ru.zalupa.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Keyboard;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.impl.combat.CheatHitBox;
import ru.zalupa.cheat.impl.combat.CheatKillAura;
import ru.zalupa.cheat.impl.combat.CheatMobAura;
import ru.zalupa.cheat.impl.misc.CheatAutoTool;
import ru.zalupa.cheat.impl.misc.CheatEffects;
import ru.zalupa.cheat.impl.misc.CheatTimer;
import ru.zalupa.cheat.impl.misc.CheatToolSave;
import ru.zalupa.cheat.impl.movement.CheatAutoSneakSprint;
import ru.zalupa.cheat.impl.movement.CheatFly;
import ru.zalupa.cheat.impl.movement.CheatLongJump;
import ru.zalupa.cheat.impl.render.CheatESP;
import ru.zalupa.cheat.impl.render.CheatHud;
import ru.zalupa.cheat.impl.render.CheatPlayerList;
import ru.zalupa.cheat.impl.world.CheatNuker;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.gui.GuiElement;
import ru.zalupa.gui.impl.Button;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public abstract class Cheat {

    //Combat
    public static CheatKillAura KILLAURA;
    public static CheatMobAura MOBAURA;
    public static CheatHitBox HITBOX;

    //Misc
    public static CheatToolSave TOOLSAVE;
    public static CheatAutoTool AUTOTOOL;
    public static CheatEffects EFFECTS;
    public static CheatTimer TIMER;

    //Movement
    public static CheatAutoSneakSprint AUTOSNEAKSPRINT;
    public static CheatFly FLY;
    public static CheatLongJump LONGJUMP;

    //Render
    public static CheatESP ESP;
    public static CheatHud HUD;
    public static CheatPlayerList PLAYERLIST;

    //World
    public static CheatNuker NUKER;

    protected static Config config = ConfigFactory.create("cheat", Zalupa.getZalupa());

    protected String name;
    protected String id;
    protected Pair<BindType, Integer> bind;
    protected SettingBoolean enabled;

    protected List<Setting<?>> settings = new LinkedList<>();

    public Cheat(String name) {
        this.name = name;
        this.id = name.toLowerCase().replace(" ", "_");
        this.settings.add(this.enabled = SettingBoolean.builder().name("Enabled").changeHandler(s -> onToggle()).build());
        this.settings.addAll(Arrays.asList(getSettings()));
        Zalupa.getZalupa().getCheatManager().register(this);
    }

    public boolean isEnabled() {
        return this.enabled.getValue();
    }

    public void onToggle() {
        if (this.enabled.getValue()) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public Setting<?>[] getSettings() {
        return (Setting<?>[])new Setting[0];
    }

    public void load() {
        String path = this.id + ".";
        this.settings.forEach(s -> s.load(path, config));
        try {
            this.bind = new Pair<>(BindType.valueOf(config.getString(path + "bind_type")), config.getInteger(path + "bind", -1));
        } catch (Exception ignored) {
            save();
            load();
        }
    }

    public void save() {
        String path = this.id + ".";
        this.settings.forEach(s -> s.save(path, config));
        config.set(path + "bind", this.bind == null ? -1 : this.bind.getRight());
        config.set(path + "bind_type", this.bind == null ? BindType.KEYBOARD.name() : this.bind.getLeft().name());
    }

    public String getBindName() {
        return "Keybind: " + ((this.bind.getRight() == -1) ? "NO" :
                bind.getLeft() == BindType.KEYBOARD ? Keyboard.getKeyName(bind.getRight()) : MouseUtil.getName(bind.getRight()));
    }

    public boolean toggle() {
        this.enabled.setValue(!this.enabled.getValue());
        onToggle();
        return this.enabled.getValue();
    }

    public final List<GuiElement> getGuiElements() {
        List<GuiElement> elements = new ArrayList<>();
        elements.add((new Button())
                .setText(getBindName())
                .setClickHandler(b -> Zalupa.getZalupa().getCheatManager().changeBind(b, this)));
        this.settings.stream().map(Setting::getElements).forEach(el -> elements.addAll(Arrays.asList(el)));
        return elements;
    }

    public void setBind(BindType type, int i) {
        this.bind = new Pair<>(type, i);
    }

    public void onEnable() {}

    public void onDisable() {}

    public abstract void onRegister();

    @AllArgsConstructor
    public enum BindType {

        MOUSE,
        KEYBOARD
    }
}

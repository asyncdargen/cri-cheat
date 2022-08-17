package ru.zalupa.cheat.impl.world;

import dev.xdark.clientapi.block.material.Material;
import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.lifecycle.GameTickPost;
import dev.xdark.clientapi.event.render.RenderPass;
import dev.xdark.clientapi.math.BlockPos;
import dev.xdark.clientapi.math.Vec3i;
import dev.xdark.clientapi.original.DiggingAction;
import dev.xdark.clientapi.original.IDargen;
import dev.xdark.clientapi.util.Collections;
import dev.xdark.clientapi.util.EnumFacing;
import dev.xdark.clientapi.world.World;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.cheat.settings.Setting;
import ru.zalupa.cheat.settings.SettingBoolean;
import ru.zalupa.cheat.settings.SettingRange;
import ru.zalupa.gui.impl.Range;
import ru.zalupa.util.RenderTools;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CheatNuker extends Cheat {

    @Getter
    protected Set<Integer> idFilter;

    protected SettingRange<Integer> upper;
    protected SettingRange<Integer> down;
    protected SettingRange<Integer> forward;
    protected SettingRange<Integer> backward;
    protected SettingRange<Integer> left;
    protected SettingRange<Integer> right;
    protected SettingRange<Integer> delay;
    protected SettingRange<Mode> mode;
    protected SettingBoolean esp;

    protected Vec3i min;
    protected Vec3i max;

    protected Vec3i current;
    protected int currentTicks;

    public CheatNuker() {
        super("Nuker");
    }

    public Setting<?>[] getSettings() {
        return new Setting<?>[]{
                upper = SettingRange.<Integer>builder()
                        .name("Up")
                        .elements(Range.range(0, 6, 1))
                        .prefix(true).build(),
                down = SettingRange.<Integer>builder()
                        .name("Down")
                        .elements(Range.range(0, 6, 1))
                        .prefix(true).build(),
                forward = SettingRange.<Integer>builder()
                        .name("Forward")
                        .elements(Range.range(0, 6, 1))
                        .prefix(true).build(),
                backward = SettingRange.<Integer>builder()
                        .name("Backward")
                        .elements(Range.range(0, 6, 1))
                        .prefix(true).build(),
                left = SettingRange.<Integer>builder()
                        .name("Left")
                        .elements(Range.range(0, 6, 1))
                        .prefix(true).build(),
                right = SettingRange.<Integer>builder()
                        .name("Right")
                        .elements(Range.range(0, 6, 1))
                        .prefix(true).build(),
                mode = SettingRange.<Mode>builder()
                        .name("Mode")
                        .elements(Arrays.asList(Mode.values()))
                        .prefix(true).build(),
                delay = SettingRange.<Integer>builder()
                        .name("Delay")
                        .elements(Range.range(0, 30, 1))
                        .index(3)
                        .nameFunction(s -> s.getValue() == 0 ? "Momental" : s.getValue().toString())
                        .prefix(true).build(),
                esp = SettingBoolean.builder()
                        .name("ESP").prefix(true)
                        .value(false).build()
        };
    }

    protected void resize(int xs, int ys, int zs, EnumFacing facing) {

        int up = this.upper.getValue();
        int down = this.down.getValue();
        int forward = this.forward.getValue();
        int backward = this.backward.getValue();
        int left = this.left.getValue();
        int right = this.right.getValue();

        int x0 = xs - backward;
        int y0 = ys - down;
        int z0 = zs - left;

        int x1 = xs + forward;
        int y1 = ys + up;
        int z1 = zs + right;

        if (facing == EnumFacing.WEST) {
            x0 = xs + backward;
            y0 = ys - down;
            z0 = zs + left;

            x1 = xs - forward;
            y1 = ys + up;
            z1 = zs - right;
        } else if (facing == EnumFacing.SOUTH) {
            x0 = xs + left;
            y0 = ys - down;
            z0 = zs - backward;

            x1 = xs - right;
            y1 = ys + up;
            z1 = zs + forward;
        } else if (facing == EnumFacing.NORTH) {
            x0 = xs - left;
            y0 = ys - down;
            z0 = zs + backward;

            x1 = xs + right;
            y1 = ys + up;
            z1 = zs - forward;
        }

        this.min = Vec3i.of(Math.min(x0, x1), Math.min(y0, y1), Math.min(z0, z1));
        this.max = Vec3i.of(Math.max(x0, x1), Math.max(y0, y1), Math.max(z0, z1));
    }

    protected boolean inBoundingZone(int x, int y, int z) {
        return min != null && max != null &&
                x >= min.getX() && x <= max.getX() &&
                y >= min.getY() && y <= max.getY() &&
                z >= min.getZ() && z <= max.getZ();
    }

    private void on() {
        EntityPlayerSP self = Zalupa.getSelfPlayer();
        if (!isEnabled() || self == null || min == null || max == null) return;

        if (delay.getValue() > 0) {
            if (current != null) {
                BlockState state = self.getWorld().getBlockState(current.getX(), current.getY(), current.getZ());
                if (!inBoundingZone(current.getX(), current.getY(), current.getZ()) ||
                        state.getBlockHardness(self.getWorld(), current.getX(), current.getY(), current.getZ()) >= 1 ||
                        !mode.getValue().isValid(state) ||
                        ++currentTicks >= delay.getValue())
                    stopBreaking();
            } else {
                EnumFacing facing = self.getHorizontalFacing();

                if (facing == EnumFacing.EAST)
                    for (int z = min.getZ(); z <= max.getZ(); z++)
                        for (int y = min.getY(); y <= max.getY(); y++)
                            for (int x = min.getX(); x <= max.getX(); x++) {
                                BlockState state = self.getWorld().getBlockState(x, y, z);
                                if (mode.getValue().isValid(state)) {
                                    startBreaking(x, y, z);
                                    break;
                                }
                            }
                else if (facing == EnumFacing.WEST)
                    for (int z = max.getZ(); z >= min.getZ(); z--)
                        for (int y = min.getY(); y <= max.getY(); y++)
                            for (int x = max.getX(); x >= min.getX(); x--) {
                                BlockState state = self.getWorld().getBlockState(x, y, z);
                                if (mode.getValue().isValid(state)) {
                                    startBreaking(x, y, z);
                                    break;
                                }
                            }
                else if (facing == EnumFacing.SOUTH)
                    for (int x = max.getX(); x >= min.getX(); x--)
                        for (int y = min.getY(); y <= max.getY(); y++)
                            for (int z = min.getZ(); z <= max.getZ(); z++) {
                                BlockState state = self.getWorld().getBlockState(x, y, z);
                                if (mode.getValue().isValid(state)) {
                                    startBreaking(x, y, z);
                                    break;
                                }
                            }
                else
                    for (int x = min.getX(); x <= max.getX(); x++)
                        for (int y = min.getY(); y <= max.getY(); y++)
                            for (int z = max.getZ(); z >= min.getZ(); z--) {
                                BlockState state = self.getWorld().getBlockState(x, y, z);
                                if (mode.getValue().isValid(state)) {
                                    startBreaking(x, y, z);
                                    break;
                                }
                            }
            }
        }
    }

    public void startBreaking(int x, int y, int z) {
        BlockPos pos = BlockPos.of(x, y, z);

        current = Vec3i.of(x, y, z);
        currentTicks = 0;

        IDargen dargen = Zalupa.getDargen();

        if (Cheat.AUTOTOOL.getNuker().getValue())
            Cheat.AUTOTOOL.equip(pos);

        dargen.blockAction(pos, DiggingAction.START_DESTROY_BLOCK, EnumFacing.DOWN);
    }

    public void stopBreaking() {
        if (current == null)
            return;

        IDargen dargen = Zalupa.getDargen();
        dargen.blockAction(BlockPos.of(current.getX(), current.getY(), current.getZ()), DiggingAction.STOP_DESTROY_BLOCK, EnumFacing.DOWN);
        current = null;
    }

    public void onDisable() {
        stopBreaking();
    }

    public void onRegister() {
        Zalupa.getZalupa().getClientApi().threadManagement().newThread(new Runnable() {
            @SneakyThrows
            public void run() {
                while (true) {
                    on();
                    Thread.sleep(50);
                }
            }
        }).start();
        RenderPass.BUS.register(event -> {
            if (min == null || max == null || !esp.getValue() || !isEnabled())
                return;

            World world = Zalupa.getApi().minecraft().getWorld();

            for (int z = min.getZ(); z <= max.getZ(); z++)
                for (int y = min.getY(); y <= max.getY(); y++)
                    for (int x = min.getX(); x <= max.getX(); x++) {
                        if (mode.getValue().isValid(world.getBlockState(x, y, z)))
                            RenderTools.drawBlockEsp(
                                    x, y, z, event.getPartialTicks(), current != null && current.getX() == x && current.getY() == y && current.getZ() == z
                            );
                    }
        });
        Zalupa.getApi().threadManagement().newThread(new Runnable() {
            @SneakyThrows
            public void run() {
                while (true) {
                    EntityPlayerSP self = Zalupa.getSelfPlayer();
                    if (min != null && max != null && isEnabled() && self != null && delay.getValue() == 0) {
                        EnumFacing facing = self.getHorizontalFacing();

                        if (facing == EnumFacing.EAST)
                            for (int z = min.getZ(); z <= max.getZ(); z++)
                                for (int y = min.getY(); y <= max.getY(); y++)
                                    for (int x = min.getX(); x <= max.getX(); x++) {
                                        BlockState state = self.getWorld().getBlockState(x, y, z);
                                        if (mode.getValue().isValid(state)) {
                                            startBreaking(x, y, z);
                                        }
                                    }
                        else if (facing == EnumFacing.WEST)
                            for (int z = max.getZ(); z >= min.getZ(); z--)
                                for (int y = min.getY(); y <= max.getY(); y++)
                                    for (int x = max.getX(); x >= min.getX(); x--) {
                                        BlockState state = self.getWorld().getBlockState(x, y, z);
                                        if (mode.getValue().isValid(state)) {
                                            startBreaking(x, y, z);
                                        }
                                    }
                        else if (facing == EnumFacing.SOUTH)
                            for (int x = max.getX(); x >= min.getX(); x--)
                                for (int y = min.getY(); y <= max.getY(); y++)
                                    for (int z = min.getZ(); z <= max.getZ(); z++) {
                                        BlockState state = self.getWorld().getBlockState(x, y, z);
                                        if (mode.getValue().isValid(state)) {
                                            startBreaking(x, y, z);
                                        }
                                    }
                        else
                            for (int x = min.getX(); x <= max.getX(); x++)
                                for (int y = min.getY(); y <= max.getY(); y++)
                                    for (int z = max.getZ(); z >= min.getZ(); z--) {
                                        BlockState state = self.getWorld().getBlockState(x, y, z);
                                        if (mode.getValue().isValid(state)) {
                                            startBreaking(x, y, z);
                                        }
                                    }
                    }
                    Thread.sleep(50);
                }
            }
        }).start();
        Zalupa.getApi().threadManagement().newThread(new Runnable() {
            @SneakyThrows
            public void run() {
                while (true) {
                    if (Zalupa.isOnline() && (isEnabled() || esp.getValue())) {
                        EntityPlayerSP self = Zalupa.getSelfPlayer();
                        resize(normal(self.getX()), normal(self.getY()), normal(self.getZ()), self.getHorizontalFacing());
                    }
                    Thread.sleep(60);
                }
            }
        }).start();
    }

    public int normal(double v) {
        int parsed = ((int) v);
        return parsed > v ? parsed - 1 : parsed;
    }

    public void load() {
        idFilter = Collections.concurrentSet();
        for (String id : config.getString("nuker.filter", "").split("; ")) {
            try {
                idFilter.add(Integer.parseInt(id));
            } catch (Throwable ignored) {}
        }
        super.load();
    }

    public void save() {
        config.set("nuker.filter", idFilter.stream().map(String::valueOf).collect(Collectors.joining("; ")));
        super.save();
    }

    @AllArgsConstructor
    public enum Mode {
        DEFAULT("All Blocks"),
        FILTER("ID Filter") {
            public boolean isValid(BlockState state) {
                return super.isValid(state) && Cheat.NUKER.idFilter.contains(state.getId());
            }
        };

        private final String name;

        public String toString() {
            return name;
        }

        public boolean isValid(BlockState state) {
            return state != null && state.getId() != 0 && state.getMaterial() != Material.AIR;
        }
    }

}
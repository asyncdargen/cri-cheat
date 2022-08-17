package ru.zalupa.cheat.impl.movement;

import dev.xdark.clientapi.block.material.Material;
import dev.xdark.clientapi.event.lifecycle.GameLoop;
import lombok.val;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;

public class CheatNoFall extends Cheat {
    public CheatNoFall() {
        super("NoFall");
    }

    @Override
    public void onRegister() {
        GameLoop.BUS.register(gameLoop -> {
            if (!isEnabled())
                return;

            if (getFallDistance() > 2F) {
                Zalupa.getDargen().fall(true);
            }
        });
    }

    public double getFallDistance() {
        val self = Zalupa.getSelfPlayer();

        if (self == null)
            return 0;

        val y = self.getY() - 1;

        for (double y1 = y; y1 > 0; y1--) {
            val state = self.getWorld().getBlockState(self.getX(), y1, self.getZ());

            if (state != null && state.getId() != 0 && state.getMaterial() != Material.AIR)
                return y - y1;
        }

        return 0;
    }
}

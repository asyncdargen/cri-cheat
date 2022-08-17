package ru.zalupa.cheat.impl.render;

import dev.xdark.clientapi.event.render.RenderPass;
import lombok.val;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.util.RenderTools;

public class CheatTracers extends Cheat {

    public CheatTracers() {
        super("Tracers");
    }

    @Override
    public void onRegister() {
        RenderPass.BUS.register(renderPass -> {
            if (!Zalupa.isOnline() || !isEnabled())
                return;

            Zalupa.getZalupa().getEntityManager().getPlayers().forEach(entity -> {
                val partialTicks = renderPass.getPartialTicks();

                float x = (float) (entity.getLastX() + (entity.getX() - entity.getLastX()) * partialTicks - entity.getX()) - ;
                float y = (float) (entity.getLastY() + (entity.getY() - entity.getLastY()) * partialTicks - entity.getY());
                float z = (float) (entity.getLastZ() + (entity.getZ() - entity.getLastZ()) * partialTicks - entity.getZ());

                RenderTools.drawTracerLine(entity, x, y, z);
            });
        });
    }
}

package ru.zalupa.cheat.impl.combat;

import dev.xdark.clientapi.event.entity.EntityLeftClick;
import lombok.val;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;

public class CheatCriticals extends Cheat {
    public CheatCriticals() {
        super("Criticals");
    }

    @Override
    public void onRegister() {
        EntityLeftClick.BUS.register(entityLeftClick -> {
            if (!isEnabled())
                return;

            val self = Zalupa.getSelfPlayer();

            if (!self.isOnGround())
                return;

           self.setMotionY(self.getMotionY() + 0.245);
           self.setMotionY(self.getMotionY() - 0.245);
        });
    }
}

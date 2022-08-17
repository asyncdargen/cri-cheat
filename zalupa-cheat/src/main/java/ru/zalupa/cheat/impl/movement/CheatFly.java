package ru.zalupa.cheat.impl.movement;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.event.lifecycle.GameLoop;
import org.lwjgl.input.Keyboard;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;

public class CheatFly extends Cheat {

    public CheatFly() {
        super("Fly");
    }

    private void on(GameLoop gameLoop) {
        EntityPlayerSP player = Zalupa.getZalupa().getClientApi().minecraft().getPlayer();
        if (player == null || !isEnabled() || Zalupa.isOpenScreen()) return;

        player.setMotion(.0, .0, .0);
        double yaw = (player.getRotationYaw() + 90.0F);
        boolean SPACE = Keyboard.isKeyDown(57);
        boolean W = Keyboard.isKeyDown(17);
        boolean A = Keyboard.isKeyDown(30);
        boolean S = Keyboard.isKeyDown(31);
        boolean D = Keyboard.isKeyDown(32);
        boolean LSHIFT = Keyboard.isKeyDown(42);
        if (LSHIFT) {
            player.setMotionY(player.getMotionY() - 0.7D);
            if (S)
                yaw += 180.0D;
            if (A)
                yaw -= 65.0D;
            if (D)
                yaw += 65.0D;
        } else if (SPACE) {
            player.setMotionY(player.getMotionY() + 0.7D);
            if (S)
                yaw += 180.0D;
            if (A)
                yaw -= 65.0D;
            if (D)
                yaw += 65.0D;
        } else if (W) {
            if (A) {
                yaw -= 65.0D;
            } else if (D) {
                yaw += 65.0D;
            }
        } else if (S) {
            yaw += 180.0D;
            if (W) {
                yaw += 65.0D;
            } else if (D) {
                yaw -= 65.0D;
            } else if (A) {
                yaw += 65.0D;
            }
        } else if (A) {
            yaw -= 90.0D;
        } else if (D) {
            yaw += 90.0D;
        }
        if (W || A || S || D) {
            player.setMotionX(Math.cos(Math.toRadians(yaw)) * 1.5D);
            player.setMotionZ(Math.sin(Math.toRadians(yaw)) * 1.5D);
        }
    }

    public void onRegister() {
        GameLoop.BUS.register(this::on);
    }
}

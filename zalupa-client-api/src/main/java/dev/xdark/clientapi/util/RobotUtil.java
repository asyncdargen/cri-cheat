package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import org.lwjgl.opengl.Display;

import java.awt.*;

@SidedApi(Side.BOTH)
public class RobotUtil {

    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {}
    }

    public static boolean isFocusedWindow() {
        return Display.isActive();
    }

    public static void pressKey(int key) {
        if (isFocusedWindow()) {
            robot.keyPress(key);
            robot.keyRelease(key);
        }
    }

    public static void pressMouse(int button) {
        if (isFocusedWindow()) {
            robot.mousePress(button);
            robot.mouseRelease(button);
        }
    }
}

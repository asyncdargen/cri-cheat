package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entry.ModMain;
import org.lwjgl.opengl.Display;

import java.nio.ByteBuffer;

@SidedApi(Side.BOTH)
public class DisplayUtil {

    public static void setTitle(String title) {
        Display.setTitle(title);
    }

    public static void setIcon(ModMain main, String ...icons) {
        ByteBuffer[] buffer = new ByteBuffer[icons.length];
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = BufferUtil.resourceImage(icons[i], main);
        Display.setIcon(buffer);
    }

}

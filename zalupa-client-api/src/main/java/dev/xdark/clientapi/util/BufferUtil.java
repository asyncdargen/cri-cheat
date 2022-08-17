package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entry.ModMain;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@SidedApi(Side.BOTH)
public class BufferUtil {

    @SneakyThrows
    public static ByteBuffer resourceImage(String img, ModMain mod) {
        try {
            ClassLoader classloader = mod.getClass().getClassLoader();
            InputStream is = classloader.getResourceAsStream(img);
            BufferedImage bufferedimage = ImageIO.read(is);
            int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null, 0, bufferedimage.getWidth());
            ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
            for (int i : aint)
                bytebuffer.putInt(i << 8 | i >> 24 & 0xFF);
            bytebuffer.flip();
            return bytebuffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

import dev.xdark.clientapi.network.NetHandler;
import dev.xdark.clientapi.network.Packet;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

public class Xy implements Packet<NetHandler> {

    public Xy(boolean onGround) {

    }

    @Override
    public void read(ByteBuf byteBuf) throws IOException {

    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {

    }

    @Override
    public void processPacket(NetHandler netHandler) {

    }
}

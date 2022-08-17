import dev.xdark.clientapi.network.NetHandler;
import dev.xdark.clientapi.network.Packet;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

public class Xi implements Packet<NetHandler> {

    public Xi(int windowId, int slot, int mouse, RX clickType, Vh itemStack, short idk) {

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

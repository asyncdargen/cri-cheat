import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.math.BlockPos;
import dev.xdark.clientapi.network.NetHandler;
import dev.xdark.clientapi.network.Packet;
import dev.xdark.clientapi.util.EnumFacing;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/*
    For compile
 */
public class XD implements Packet<NetHandler> {

    public XD(XE action, acV pos, abP facing) {

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

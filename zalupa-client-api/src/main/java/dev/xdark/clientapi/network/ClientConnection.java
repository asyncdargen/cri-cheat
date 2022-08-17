package dev.xdark.clientapi.network;

import com.mojang.authlib.GameProfile;
import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import io.netty.buffer.ByteBuf;
import java.util.Collection;
import java.util.UUID;

@SidedApi(Side.BOTH)
public interface ClientConnection extends NetHandler {

  @SidedApi(Side.BOTH)
  void addPlayerInfo(NetworkPlayerInfo info);

  NetworkPlayerInfo getPlayerInfo(UUID uuid);

  NetworkPlayerInfo getPlayerInfo(String name);

  @SidedApi(Side.BOTH)
  NetworkPlayerInfo removePlayerInfo(UUID uuid);

  @SidedApi(Side.BOTH)
  NetworkPlayerInfo removePlayerInfo(String name);

  Collection<NetworkPlayerInfo> getPlayerInfos();

  @SidedApi(Side.BOTH)
  void sendPayload(String channel, ByteBuf buf);

  @SidedApi(Side.BOTH)
  NetworkPlayerInfo newPlayerInfo(GameProfile profile);

  @SidedApi(Side.BOTH)
  void sendPacket(Packet<?> packet);
}

package dev.xdark.clientapi.event.chunk;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.event.Event;
import dev.xdark.clientapi.event.EventBus;
import dev.xdark.clientapi.util.SideEffects;
import dev.xdark.clientapi.world.chunk.Chunk;

@SidedApi(Side.BOTH)
public interface ChunkLoad extends Event {

  EventBus<ChunkLoad> BUS = SideEffects.objectValue();

  Chunk getChunk();
}

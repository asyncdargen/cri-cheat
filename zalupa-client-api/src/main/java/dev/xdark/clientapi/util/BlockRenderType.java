package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import static dev.xdark.clientapi.util.SideEffects.objectValue;

@SidedApi(Side.BOTH)
public interface BlockRenderType {

  BlockRenderType INVISIBLE = objectValue(),
      LIQUID = objectValue(),
      ENTITYBLOCK_ANIMATED = objectValue(),
      MODEL = objectValue();
}

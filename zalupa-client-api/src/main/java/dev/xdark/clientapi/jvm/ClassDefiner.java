package dev.xdark.clientapi.jvm;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

@SidedApi(Side.BOTH)
public interface ClassDefiner {

  Class<?> defineClass(String name, byte[] code, int off, int len);
}

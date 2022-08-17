package dev.xdark.clientapi.network;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import java.util.concurrent.Executor;

@SidedApi(Side.BOTH)
public interface NetHandler {

    Executor executor();
}

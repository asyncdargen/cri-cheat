package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import io.netty.util.internal.ConcurrentSet;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SidedApi(Side.BOTH)
public class Collections {

    public static <K, V> Map<K, V> concurrentMap() {
        return new ConcurrentHashMap<>();
    }

    public static <V> Set<V> concurrentSet() {
        return new ConcurrentSet<>();
    }

}

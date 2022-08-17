package dev.xdark.clientapi.util.reflect;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import java.lang.reflect.Method;

@SidedApi(Side.BOTH)
public interface MethodAccessor<R> {

    R invoke(Object instance, Object... params);

    boolean has(Class<?> clazz);

    boolean has(Object instance);

    Method getMethod();

}

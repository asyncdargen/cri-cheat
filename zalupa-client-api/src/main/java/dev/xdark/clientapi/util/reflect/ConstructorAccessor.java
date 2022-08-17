package dev.xdark.clientapi.util.reflect;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import java.lang.reflect.Constructor;

@SidedApi(Side.BOTH)
public interface ConstructorAccessor<T> {

    T allocateInstance();

    T getInstance(Object... params);

    Constructor<T> getConstructor();

    Class<T> getDeclaringClass();

}

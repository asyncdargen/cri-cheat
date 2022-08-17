package dev.xdark.clientapi.util.reflect;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;

import java.lang.reflect.Field;

@SidedApi(Side.BOTH)
public interface FieldAccessor<T> {

    T get(Object instance);

    void set(Object instance, T value);

    boolean has(Object instance);

    boolean has(Class<?> clazz);

    Field getField();
    
}

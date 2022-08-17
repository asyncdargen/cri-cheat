package dev.xdark.clientapi.original;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.reflect.Reflection;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@SidedApi(Side.BOTH)
public enum DiggingAction {

    START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM, SWAP_HELD_ITEMS;

    protected static Object[] handles;

    static {
        Class<?> xe = Reflection.getClass("XE");
        for (Field field : Reflection.getFields(xe)) {
            if (field.getType().isArray() && Modifier.isStatic(field.getModifiers())) {
                handles = Reflection.getValue(field, null);
                if (handles.length == 7) break;
            }
        }
    }


    public Object getHandle() {
        return handles[ordinal()];
    }
}

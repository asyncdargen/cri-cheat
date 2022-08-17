package dev.xdark.clientapi.util.reflect;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SidedApi(Side.BOTH)
@SuppressWarnings("ALL")
public class UnsafeUtils {

    private static Unsafe UNSAFE;
    private static MethodHandles.Lookup LOOKUP;

    static {
        try {
            //Unsafe
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field unsafeField = unsafeClass.getDeclaredField("theUnsafe");
            if (!unsafeField.isAccessible())
                unsafeField.setAccessible(true);
            UNSAFE = (Unsafe) unsafeField.get(null);
            //Lookup
            LOOKUP = MethodHandles.lookup();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    @SneakyThrows
    public static <T> T allocateInstance(Class<T> to) {
        return (T) getUnsafe().allocateInstance(to);
    }

    @SneakyThrows
    public static MethodHandle unreflectMethod(Method method) {
        return LOOKUP.unreflect(method);
    }

}

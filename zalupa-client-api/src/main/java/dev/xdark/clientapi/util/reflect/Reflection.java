package dev.xdark.clientapi.util.reflect;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@SidedApi(Side.BOTH)
@UtilityClass
public class Reflection {

    @SneakyThrows
    public Field getField(Class<?> clazz, String field) {
        return clazz.getDeclaredField(field);
    }

    @SneakyThrows
    public Field getField(Object obj, String field) {
        return getField(obj.getClass(), field);
    }

    @SneakyThrows
    public Field[] getFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    @SneakyThrows
    public Field[] getFields(Object obj) {
        return getFields(obj.getClass());
    }

    public static Field findField(Class<?> declaredClass, Class<?> fieldClass) {
        for (Field field : getFields(declaredClass)) {
            if (fieldClass == field.getType())
                return field;
        }
        return null;
    }

    @SneakyThrows
    public void setValue(Field field, Object instance, Object value) {
        if (!field.isAccessible())
            field.setAccessible(true);
        field.set(instance, value);
    }

    @SneakyThrows
    public void setValue(String field, Object instance, Object value) {
        setValue(getField(instance, field), instance, value);
    }

    @SneakyThrows
    public <T> T getValue(Field field, Object instance) {
        if (!field.isAccessible())
            field.setAccessible(true);
        return (T) field.get(instance);
    }

    @SneakyThrows
    public <T> T getValue(String field, Object instance) {
        return getValue(getField(instance, field), instance);
    }

    @SneakyThrows
    public Method getMethod(Class<?> clazz, String method, Class<?>... params) {
        return clazz.getDeclaredMethod(method, params);
    }

    @SneakyThrows
    public Method getMethod(Object object, String method, Class<?>... params) {
        return getMethod(object.getClass(), method, params);
    }

    @SneakyThrows
    public Method[] getMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    @SneakyThrows
    public Method[] getMethods(Object obj) {
        return getMethods(obj.getClass());
    }

    @SneakyThrows
    public <T> T invokeMethod(Method method, Object instance, Object... params) {
        return (T) method.invoke(instance, params);
    }

    @SneakyThrows
    public <T> T invokeMethod(String method, Object instance, Object... params) {
        return invokeMethod(getMethod(instance, method), instance, params);
    }

    @SneakyThrows
    public Class<?> getClass(String path) {
        return Class.forName(path);
    }

    @SneakyThrows
    public Class<?>[] getClasses(Class<?> clazz) {
        return clazz.getClasses();
    }

    @SneakyThrows
    public <T> Constructor<T>[] getConstructors(Class<T> clazz) {
        return (Constructor<T>[]) clazz.getDeclaredConstructors();
    }

    @SneakyThrows
    public <T> Constructor<T>[] getConstructors(T obj) {
        return (Constructor<T>[]) getConstructors(obj.getClass());
    }

    @SneakyThrows
    public <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... params) {
        return clazz.getDeclaredConstructor(params);
    }

    @SneakyThrows
    public <T> T getInstance(Constructor<T> constructor, Object... params) {
        return constructor.newInstance(params);
    }

    private static <T> T allocateInstance(Class<T> declaringClass) {
        return UnsafeUtils.allocateInstance(declaringClass);
    }

    @SneakyThrows
    public <T> ConstructorAccessor<T> constructorAccessor(Constructor<T> constructor) {
        return new ConstructorAccessorImpl<>(constructor, constructor.getDeclaringClass());
    }

    public <T> FieldAccessor<T> fieldAccessor(Field field) {
        return new FieldAccessorImpl<>(field);
    }

    public <R> MethodAccessor<R> methodAccessor(Method method) {
        return new MethodAccessorImpl<>(method);
    }

    @SidedApi(Side.BOTH)
    @AllArgsConstructor @Getter
    static class FieldAccessorImpl<T> implements FieldAccessor<T> {


        private final Field field;

        public T get(Object instance) {
            return getValue(field, instance);
        }

        public void set(Object instance, T value) {
            setValue(field, instance, value);
        }

        public boolean has(Object instance) {
            return has(instance.getClass());
        }

        public boolean has(Class<?> clazz) {
            return Arrays.asList(getFields(clazz)).contains(field);
        }

    }
    @SidedApi(Side.BOTH)
    @AllArgsConstructor @Getter
    static class MethodAccessorImpl<R> implements MethodAccessor<R> {

        private final Method method;

        public R invoke(Object instance, Object... params) {
            return invokeMethod(method, instance, params);
        }

        public boolean has(Class<?> clazz) {
            return Arrays.asList(getMethods(clazz)).contains(method);
        }

        public boolean has(Object instance) {
            return has(instance.getClass());
        }

    }
    @SidedApi(Side.BOTH)
    @AllArgsConstructor @Getter
    static class ConstructorAccessorImpl<T> implements ConstructorAccessor<T> {

        private final Constructor<T> constructor;

        private final Class<T> declaringClass;

        public T allocateInstance() {
            return Reflection.allocateInstance(declaringClass);
        }

        public T getInstance(Object... params) {
            return Reflection.getInstance(constructor, params);
        }

    }
}

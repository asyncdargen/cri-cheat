package dev.xdark.clientapi.event;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.util.BusUtil;

import java.util.function.Consumer;

@SidedApi(Side.BOTH)
public interface EventBus<E extends Event> {

    Listener createListener();

    default RegisteredListener register(Consumer<E> handler) {
        RegisteredListener registeredListener = register(handler, 100);
        BusUtil.add(registeredListener);
        return registeredListener;
    }

    default RegisteredListener register(Consumer<E> handler, int priority) {
        return register(createListener(), handler, priority);
    }

    RegisteredListener register(Listener listener, Consumer<E> handler, int priority);

    void fireAndForget(E event);

    E fire(E event);

    @Deprecated
    <V> RegisteredListener register(
            Listener listener, Class<V> action, Consumer<V> handler, int priority);

    @Deprecated
    <V> void unregister(Class<V> action, Consumer<V> handler);

    void unregisterAll(Listener listener);

    @Deprecated
    <V> V post(Class<V> action, V value);

    @Deprecated
    boolean anyListeners(Class<?> action);
}

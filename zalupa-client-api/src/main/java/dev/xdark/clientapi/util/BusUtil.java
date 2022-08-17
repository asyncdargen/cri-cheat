package dev.xdark.clientapi.util;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.event.RegisteredListener;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
@SidedApi(Side.BOTH)
public class BusUtil {

    public List<RegisteredListener> registeredListeners = new ArrayList<>();

    public void add(RegisteredListener listener) {
        registeredListeners.add(listener);
    }

    public void unregisterAll() {
        registeredListeners.forEach(RegisteredListener::unregister);
        registeredListeners.clear();
    }

}

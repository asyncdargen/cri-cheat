package ru.zalupa.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MouseUtil {

    public String getName(int button) {
        switch (button) {
            case 0:
                return "LEFT";
            case 1:
                return "RIGHT";
            case 2:
                return "MIDDLE";
        }

        return "BUTTON" + button;
    }
}

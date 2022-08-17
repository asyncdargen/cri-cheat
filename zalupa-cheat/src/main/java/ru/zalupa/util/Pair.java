package ru.zalupa.util;

import lombok.Getter;

@Getter
public class Pair<Left, Right> {

    private final Left left;
    private final Right right;

    public Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }
}

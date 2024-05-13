package com.aditya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bomb {
    private final Position position;
    private final int count;
}

package com.aditya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerChance {
    private final Player player;
    private final Position position;
}

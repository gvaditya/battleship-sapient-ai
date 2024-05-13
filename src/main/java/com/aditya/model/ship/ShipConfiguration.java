package com.aditya.model.ship;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShipConfiguration {
    private final ShipType shipType;
    private final int length;
    private final int width;
}

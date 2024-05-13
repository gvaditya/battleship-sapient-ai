package com.aditya.model.ship;

import com.aditya.model.boundary.IBoundary;
import lombok.ToString;

@ToString
public class PShip extends Ship{
    public PShip(IBoundary boundary) {
        super(1, boundary);
    }
}

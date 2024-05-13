package com.aditya.model.ship;

import com.aditya.model.boundary.IBoundary;
import lombok.ToString;

@ToString
public class QShip extends Ship{
    public QShip(IBoundary boundary) {
        super(2, boundary);
    }
}

package com.aditya.model.boundary;

import com.aditya.model.Position;

import java.util.List;

public interface IBoundary {

    List<Position> getAllPositions();
    boolean contains(Position position);
}

package com.aditya.model.ship;

import com.aditya.model.Position;

import java.util.Map;

public interface IPiece {
    boolean isKilled(Map<String,Integer> bombs);
    boolean containsPosition(Position position);
}

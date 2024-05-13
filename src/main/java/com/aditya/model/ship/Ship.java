package com.aditya.model.ship;

import com.aditya.model.Position;
import com.aditya.model.boundary.IBoundary;
import lombok.NonNull;

import java.util.Map;

abstract class Ship implements IPiece {

    private final int hitsPerPosition;
    private final IBoundary boundary;

    Ship(int hitsPerPosition, IBoundary boundary) {
        this.hitsPerPosition = hitsPerPosition;
        this.boundary = boundary;
    }


    @Override
    public boolean isKilled(Map<String, Integer> bombs) {
        for (Position position: boundary.getAllPositions()){
            if(bombs.getOrDefault(position.toString(),0) < hitsPerPosition){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsPosition(@NonNull Position position) {
        return boundary.contains(position);
    }

}

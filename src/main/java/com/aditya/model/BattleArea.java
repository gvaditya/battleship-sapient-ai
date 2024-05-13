package com.aditya.model;

import com.aditya.exceptions.OutOfBoundPositionException;
import com.aditya.model.boundary.IBoundary;
import com.aditya.model.ship.IPiece;
import lombok.Getter;
import lombok.NonNull;

import java.util.*;

@Getter
public class BattleArea {
    List<IPiece> pieces;
    IBoundary boundary;
    Map<String,Integer> bombs;
    Set<Position> hitLocations = new HashSet<>();
    Set<Position> missLocations = new HashSet<>();

    public BattleArea(@NonNull List<IPiece> pieces,@NonNull IBoundary boundary) {
        this.pieces = pieces;
        this.boundary = boundary;
        this.bombs = new HashMap<>();
    }

    public boolean takeHit( @NonNull Position position) throws OutOfBoundPositionException {
        if(!boundary.contains(position)){
            throw new OutOfBoundPositionException();
        }
        bombs.put(position.toString(),bombs.getOrDefault(position.toString(),0)+1);
        for (IPiece piece : pieces) {
            if(piece.containsPosition(position)){
                hitLocations.add(position);
                return true;
            }
        }
        missLocations.add(position);
        return false;
    }

    public boolean isDefeated(){
        for (IPiece piece : pieces) {
            if(!piece.isKilled(bombs)){
                return false;
            }
        }
        return true;
    }
}

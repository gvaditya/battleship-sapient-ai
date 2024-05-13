package com.aditya.model.boundary;

import com.aditya.exceptions.InvalidPositionException;
import com.aditya.model.Position;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class RectangularBoundary implements IBoundary{

    private final List<Position> allPositions;
    private final Position top;
    private final Position bottom;

    public RectangularBoundary(@NonNull Position top,@NonNull Position bottom) throws InvalidPositionException {
        this.top = top;
        this.bottom = bottom;
        validatePositions();
        this.allPositions = this.initialiseAllPositions();
    }

    private void validatePositions() throws InvalidPositionException {
        if(top.getX()>bottom.getX() || top.getY()> bottom.getY()){
            throw new InvalidPositionException();
        }
    }

    private List<Position> initialiseAllPositions(){
        List<Position> positions = new ArrayList<>();
        for (int x = top.getX(); x <= bottom.getX(); x++) {
            for (int y = top.getY(); y <= bottom.getY() ; y++) {
                positions.add(new Position(x,y));
            }
        }
        return positions;
    }

    @Override
    public boolean contains(@NonNull Position position) {
        return top.getX()<= position.getX() && position.getX()<= bottom.getX()
                && top.getY()<= position.getY() && position.getY() <= bottom.getY();
    }
}

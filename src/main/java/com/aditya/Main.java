package com.aditya;


import com.aditya.exceptions.InvalidInputException;
import com.aditya.exceptions.InvalidPositionException;
import com.aditya.io.*;
import com.aditya.logic.DefaultChanceLogic;
import com.aditya.logic.DefaultWinnerLogic;
import com.aditya.logic.RoundRobinPlayerPicking;
import com.aditya.model.BattleArea;
import com.aditya.model.Player;
import com.aditya.model.Position;
import com.aditya.model.boundary.IBoundary;
import com.aditya.model.boundary.RectangularBoundary;
import com.aditya.model.ship.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidInputException, IOException {
        System.out.println("Please enter the size of the battle area in format x y");
        IPositionProvider positionProvider = new SysInPositionProvider();
        IBoundary boundary = null;
        while(boundary == null){
            try {
                boundary = new RectangularBoundary(new Position(1,1),positionProvider.takeInput());
            } catch (IOException | InvalidPositionException e) {
                System.out.println("Please retry again");
            } catch (InvalidInputException e) {
                System.out.println("Please enter only two integers");
            }
        }
        List<ShipConfiguration> shipsAllowed = getShipConfiguration();
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            players.add(getPlayer(i,positionProvider,boundary,shipsAllowed));
        }
        Game game = new Game(players,new RoundRobinPlayerPicking(), new DefaultWinnerLogic(), new Printer());
        game.start();
    }

    private static List<ShipConfiguration> getShipConfiguration() throws IOException {
        List<ShipConfiguration> response = new ArrayList<>();
        IShipConfigurationProvider shipConfigurationProvider = new SysInShipConfigurationProvider();
        System.out.println("Please enter pieces configuration each player gets");
        while (true){
            System.out.println("Please enter configuration as shipType length width");
            try {
                response.add(shipConfigurationProvider.takeInput());
            } catch (InvalidInputException e){
                break;
            }
        }
        return response;
    }

    private static Player getPlayer(int i,IPositionProvider positionProvider,
                                    IBoundary boundary, List<ShipConfiguration> shipsAllowed){
        List<IPiece> pieces = new ArrayList<>();
        System.out.println("Player "+i);
        for (ShipConfiguration shipConfiguration : shipsAllowed) {
            System.out.println("For ship type: "+shipConfiguration.getShipType()+" with length :"
                    +shipConfiguration.getLength()+" and width: "+shipConfiguration.getWidth());
            switch(shipConfiguration.getShipType()) {
                case P:
                    pieces.add(new PShip(getBoundary(positionProvider,boundary,shipConfiguration)));
                    break;
                case Q:
                    pieces.add(new QShip(getBoundary(positionProvider,boundary,shipConfiguration)));
                    break;
            }
        }
        BattleArea battleArea = new BattleArea(pieces,boundary);
        return new Player(battleArea, i, new DefaultChanceLogic(5,positionProvider));
    }

    private static RectangularBoundary getBoundary(IPositionProvider positionProvider, IBoundary boundary,
                                                   ShipConfiguration shipConfiguration){
        while (true){
            System.out.println("Please enter the top position");
            Position top = getPosition(positionProvider,boundary);
            System.out.println("Please enter the bottom position");
            Position bottom = getPosition(positionProvider,boundary);
            int X = bottom.getX() - top.getX() + 1;
            int Y = bottom.getY() - top.getY() + 1;
            if(Math.min(X,Y)!= shipConfiguration.getWidth()){
                System.out.println("Width should be "+shipConfiguration.getWidth());
                continue;
            }
            if(Math.max(X,Y) != shipConfiguration.getLength()){
                System.out.println("Length should be "+shipConfiguration.getLength());
                continue;
            }
            try{
                return new RectangularBoundary(top,bottom);
            } catch (InvalidPositionException e){
                System.out.println("Top position should be lesser in x and y values");
            }
        }
    }

    private static Position getPosition(IPositionProvider positionProvider, IBoundary boundary){
        while(true){
            try {
                Position position = positionProvider.takeInput();
                if(!boundary.contains(position)){
                    System.out.println("Position is out of boundary please re-enter");
                } else{
                    return position;
                }
            } catch (IOException e) {
                System.out.println("Please retry again");
            } catch (InvalidInputException e) {
                System.out.println("Please enter only two integers");
            }
        }
    }
}
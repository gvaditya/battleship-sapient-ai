package com.aditya.io;

import com.aditya.model.Player;
import lombok.NonNull;

import java.util.List;

public class Printer {

    public void print(String message){
        System.out.println(message);
    }

    private void printPlayerInfo( Player player) {
        print("Player: " + player.getId());
    }

    public void printSelfBoard(Player player) {
        print("Your board status: ");
        printPlayerInfo(player);
        print("Ships: " + player.getBattleArea().getPieces());
        print("Hit locations: " + player.getBattleArea().getHitLocations());
        print("Missed locations: " + player.getBattleArea().getMissLocations());
    }

    private void printOpponentBoard(Player player){
        print("Opponent board status: ");
        printPlayerInfo(player);
        print("Hit locations: " + player.getBattleArea().getHitLocations());
        print("Missed locations: " + player.getBattleArea().getMissLocations());

    }

    public void printOpponentBoard( List<Player> opponents) {
        for (Player player : opponents) {
            printOpponentBoard(player);
        }
    }
}

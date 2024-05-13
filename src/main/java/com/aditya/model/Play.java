package com.aditya.model;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.logic.IChanceLogic;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Play {

    private final IChanceLogic chanceLogic;


    public List<Player> getOpponents(int currentPlayerIndex, List<Player> players) {
        List<Player> list = new ArrayList<>();
        for (Player it : players) {
            if (currentPlayerIndex+1 != it.getId()) {
                list.add(it);
            }
        }
        return list;
    }


    public void playChance(Player player, List<Player> opponents) throws InvalidInputException, IOException {
        chanceLogic.playChance(opponents, player.getMissilesUsed());
    }
}

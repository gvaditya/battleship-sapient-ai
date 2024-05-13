package com.aditya.logic;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.model.Player;

import java.util.List;

public class RoundRobinPlayerPicking implements IPlayerPickingLogic{
    @Override
    public Integer firstPlayer(List<Player> allPlayers) throws InvalidInputException {
        if (allPlayers.isEmpty()) {
            throw new InvalidInputException();
        }
        return 0;
    }

    @Override
    public Integer pickNextPlayer(Integer currentPlayerIndex, List<Player> allPlayers) {
        return (currentPlayerIndex + 1) % allPlayers.size();
    }
}

package com.aditya.logic;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.model.Player;

import java.util.List;

public interface IPlayerPickingLogic {

    Integer firstPlayer(List<Player> allPlayers) throws InvalidInputException;
    Integer pickNextPlayer(Integer currentPlayerIndex, List<Player> allPlayers);
}

package com.aditya.logic;

import com.aditya.exceptions.MissilesExhaustedException;
import com.aditya.model.Player;

import java.util.List;

public interface IWinnerLogic {
    Player getWinner(List<Player> playerList) throws MissilesExhaustedException;
}

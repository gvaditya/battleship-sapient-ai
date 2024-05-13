package com.aditya.logic;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.model.Player;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IChanceLogic {
    void playChance(List<Player> opponents, Map<Integer,Integer> missilesUsed) throws InvalidInputException, IOException;
    boolean missilesExhausted(List<Player> opponents,Map<Integer,Integer> missilesUsed);
}

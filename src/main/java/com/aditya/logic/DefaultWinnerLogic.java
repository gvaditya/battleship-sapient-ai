package com.aditya.logic;

import com.aditya.exceptions.MissilesExhaustedException;
import com.aditya.model.Player;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DefaultWinnerLogic implements IWinnerLogic{
    @Override
    public Player getWinner(List<Player> playerList) throws MissilesExhaustedException {
        List<Player> alivePlayers = playerList.parallelStream()
                .filter(it->!it.isDefeated()).collect(Collectors.toList());
        if (alivePlayers.size() == 1) {
            return alivePlayers.get(0);
        } else{
            long exhausted = alivePlayers.parallelStream()
                    .filter(it->it.missilesExhausted(alivePlayers)).count();
            if((int) exhausted == alivePlayers.size()){
                throw new MissilesExhaustedException();
            }
        }
        return null;
    }
}

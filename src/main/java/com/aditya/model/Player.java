package com.aditya.model;

import com.aditya.exceptions.OutOfBoundPositionException;
import com.aditya.logic.IChanceLogic;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Player {

    private final BattleArea battleArea;
    private final int id;
    private final IChanceLogic chanceLogic;
    private final Map<Integer, Integer> missilesUsed;

    public Player(BattleArea battleArea, int id, IChanceLogic chanceLogic) {
        this.battleArea = battleArea;
        this.id = id;
        this.chanceLogic = chanceLogic;
        this.missilesUsed = new HashMap<>();
    }


    public boolean isDefeated(){
        return battleArea.isDefeated();
    }

    public boolean missilesExhausted(List<Player> players){
        List<Player> opponents = getOpponentsForMissiles(players);
        return chanceLogic.missilesExhausted(opponents, missilesUsed);
    }

    public boolean takeHit(Position position) throws OutOfBoundPositionException {
        return battleArea.takeHit(position);
    }

    private List<Player> getOpponentsForMissiles(List<Player> players) {
        List<Player> list = new ArrayList<>();
        for (Player it : players) {
            if (this.id != it.getId() && !it.isDefeated()) {
                list.add(it);
            }
        }
        return list;
    }

}

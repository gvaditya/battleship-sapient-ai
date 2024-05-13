package com.aditya.logic;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.exceptions.OutOfBoundPositionException;
import com.aditya.io.IPositionProvider;
import com.aditya.model.Player;
import com.aditya.model.Position;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class DefaultChanceLogic implements IChanceLogic{
    private final int missilesAllowed;
    private final IPositionProvider inputProvider;

    @Override
    public void playChance(List<Player> opponents, Map<Integer, Integer> missilesUsed) throws InvalidInputException, IOException {
        for (Player opponent : opponents) {
            Integer missiles = missilesUsed.getOrDefault(opponent.getId(),0);
            if(!opponent.isDefeated() && missiles<missilesAllowed){
                missiles = playChance(opponent,missiles);
            }
            missilesUsed.put(opponent.getId(),missiles);
        }
    }

    @Override
    public boolean missilesExhausted(List<Player> opponents,Map<Integer, Integer> missilesUsed) {
        for (Player opponent : opponents) {
            if(missilesUsed.getOrDefault(opponent.getId(),0) < missilesAllowed){
                return false;
            }
        }
        return true;
    }

    private Integer playChance(Player opponent, Integer missiles) throws InvalidInputException, IOException {
        boolean play = true;
        while (play){
            System.out.println("Play against Player "+opponent.getId());
            Position playerInput = inputProvider.takeInput();
            try {
                boolean hit = opponent.takeHit(playerInput);
                missiles++;
                if(hit){
                    boolean defeated = opponent.isDefeated();
                    if(defeated){
                        System.out.println("Opponent is defeated!!");
                        play = false;
                    }
                    else if(missiles == missilesAllowed){
                        System.out.println("Opponent was hit, but you have exhausted the missiles");
                    } else{
                        System.out.println("Opponent was hit. Play Again!!");
                    }
                } else{
                    System.out.println("It was miss!!");
                    if(missiles == missilesAllowed){
                        System.out.println("You have exhausted the missiles");
                    }
                    play = false;
                }
            } catch (OutOfBoundPositionException e) {
                System.out.println("Position is out of bound");
            }
        }
        return missiles;
    }
}

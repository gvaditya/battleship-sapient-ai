package com.aditya;

import com.aditya.exceptions.InvalidInputException;
import com.aditya.exceptions.MissilesExhaustedException;
import com.aditya.io.Printer;
import com.aditya.io.SysInPositionProvider;
import com.aditya.logic.DefaultChanceLogic;
import com.aditya.logic.IPlayerPickingLogic;
import com.aditya.logic.IWinnerLogic;
import com.aditya.model.Play;
import com.aditya.model.Player;

import java.io.IOException;
import java.util.List;

public class Game {

    private final List<Player> players;
    private final IPlayerPickingLogic nextPlayerLogic;
    private final IWinnerLogic winnerLogic;
    private final Printer printer;

    public Game(List<Player> players, IPlayerPickingLogic nextPlayerLogic, IWinnerLogic winnerLogic, Printer printer) {
        this.players = players;
        this.nextPlayerLogic = nextPlayerLogic;
        this.winnerLogic = winnerLogic;
        this.printer = printer;
    }
    public void start() throws InvalidInputException, IOException {
        Play play = new Play(new DefaultChanceLogic(5, new SysInPositionProvider()));
        int currentPlayerIndex = nextPlayerLogic.firstPlayer(this.players);
        System.out.println("Starting game!");
        while (true){
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Player: " + currentPlayer.getId() + " chance:");
            List<Player> list = play.getOpponents(currentPlayerIndex,players);

            play.playChance(currentPlayer,list);

            printer.printSelfBoard(currentPlayer);
            printer.printOpponentBoard(list);

            try {
                Player winner = winnerLogic.getWinner(players);
                if(winner !=null){
                    printer.print("Player "+winner.getId()+" WINS!!");
                    break;
                }
            } catch (MissilesExhaustedException e) {
                printer.print("All players have exhausted their missiles!!");
                break;
            }
            currentPlayerIndex = nextPlayerLogic.pickNextPlayer(currentPlayerIndex,this.players);
        }
    }

}

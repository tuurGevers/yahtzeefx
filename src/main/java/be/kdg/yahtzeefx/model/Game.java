package be.kdg.yahtzeefx.model;

import java.util.Date;

public class Game {
    private int gameId;
    private Date startTime;
    private Date endTime;

    public Game(int gameId, Date startTime, Date endTime) {
        this.gameId = gameId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //start game
    public void startGame(){}

    //start round
    public void playRound(){}

    //end game
    public void endGame(){}
}

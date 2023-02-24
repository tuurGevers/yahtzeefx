package be.kdg.yahtzeefx.model;

import java.util.Arrays;

public class Player {
    private int playerId;
    private String name;
    public Score score;

    public Player(int playerId, String name, Score score) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
    }




    //add the selected score to the players score
    public void addScore(int score){
        this.score.addScore(score);
    }
}

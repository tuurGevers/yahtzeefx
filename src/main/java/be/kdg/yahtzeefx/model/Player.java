package be.kdg.yahtzeefx.model;

public class Player {
    private int playerId;
    private String name;
    private int score;

    public Player(int playerId, String name, int score) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
    }

    //roll all dice
    public int[] rollDice(){return new int[]{0,1};}
    //let user select wich dice to hold
    public void selectDiceToHold(){}
    //starts the scoring process
    public void scoreRound(){}
    //add the selected score to the players score
    public void addScore(){}
}

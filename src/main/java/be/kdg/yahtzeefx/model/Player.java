package be.kdg.yahtzeefx.model;

public class Player {
    private int playerId;
    private String name;
    public Score score;

    public Player(int playerId, String name, Score score) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return playerId;
    }
}

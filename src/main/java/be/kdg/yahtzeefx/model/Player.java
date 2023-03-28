package be.kdg.yahtzeefx.model;

public class Player {
    private final int playerId;
    private final String name;
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

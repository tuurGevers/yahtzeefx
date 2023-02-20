package be.kdg.yahtzeefx.model;

public class Score  {
    private int points;
    private Player player;

    public Score(Player player) {
        this.player = player;
        this.points= 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
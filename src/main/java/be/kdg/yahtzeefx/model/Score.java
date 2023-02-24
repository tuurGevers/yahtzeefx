package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

public class Score  {
    private int points;
    public Map<String, Integer> scores;
    public Score() {
        this.points= 0;
        this.scores = new HashMap<>();
    }

    public void addScore(int score){
        this.points+=score;
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

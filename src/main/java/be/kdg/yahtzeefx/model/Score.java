package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

public class Score  {
    private int points;
    public Map<String, Integer> scores;
    public boolean upperBonus;
    public Score() {
        this.points= 0;
        this.scores = new HashMap<>();
        this.upperBonus = false;
    }

    //voeg score toe
    public void addScore(int score){
        this.points+=score;
    }

    public String getPoints() {
        return String.valueOf(points);
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

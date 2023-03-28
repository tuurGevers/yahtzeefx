package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Score.
 */
public class Score  {
    private int points;
    /**
     * The Scores.
     */
    public Map<String, Integer> scores;
    /**
     * The Upper bonus.
     */
    public boolean upperBonus;

    /**
     * Instantiates a new Score.
     */
    public Score() {
        this.points= 0;
        this.scores = new HashMap<>();
        this.upperBonus = false;
    }

    /**
     * Add score.
     *
     * @param score the score
     */
//voeg score toe
    public void addScore(int score){
        this.points+=score;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public String getPoints() {
        return String.valueOf(points);
    }

}

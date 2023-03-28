package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Scoring.
 */
public abstract class Scoring {
    /**
     * The Id.
     */
    protected int id;

    /**
     * Instantiates a new Scoring.
     *
     * @param id the id
     */
    public Scoring(int id){
        this.id = id;
    }

    /**
     * Gets score.
     *
     * @param dice the dice
     * @return the score
     */
    protected abstract int getScore(Dice[] dice);

    /**
     * Is valid boolean.
     *
     * @param dice the dice
     * @return the boolean
     */
    protected abstract boolean isValid(Dice[] dice);

    /**
     * Gets frequenty.
     *
     * @param dice the dice
     * @return the frequenty
     */
//maakt een map van alle mogelijke stenen 1..6 en houd de fresunetie ervan bij
    public Map<Integer, Integer> getFrequenty(Dice[] dice) {
        Map<Integer, Integer> frequency = new HashMap<>();
        //voor elke steen word in frequency gestoken en
        // de waarde wordt gelijkgesteld aan de oude waarde+1 of nul als die nog niet bestaat
        for (Dice d : dice) {
            int value = d.getValue();
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }
        return frequency;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }
}

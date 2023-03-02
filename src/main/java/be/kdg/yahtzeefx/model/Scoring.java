package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Scoring {
    private String name;

    protected abstract int getScore(Dice[] dice);

    protected abstract boolean isValid(Dice[] dice);

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
}

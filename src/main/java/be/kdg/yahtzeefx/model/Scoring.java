package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Scoring {
    private String name;

    protected abstract int getScore(Dice[] dice);

    protected abstract boolean isValid(Dice[] dice);

    public Map<Integer, Integer> getFrequenty(Dice[] dice) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Dice d : dice) {
            int value = d.getValue();
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }
        return frequency;
    }
}

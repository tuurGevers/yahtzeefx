package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class Chance extends Scoring {
    @Override
    public int getScore(Dice[] dice) {
        int sum = 0;
        for (Dice d : dice) {
            sum += d.getValue();
        }
        return sum;
    }

    @Override
    public boolean isValid(Dice[] dice) {
        return true;
    }
}

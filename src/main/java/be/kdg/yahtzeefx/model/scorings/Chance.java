package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class Chance extends Scoring {
    //totaal punten
    @Override
    public int getScore(Dice[] dice) {
        int sum = 0;
        for (Dice d : dice) {
            sum += d.getValue();
        }
        return sum;
    }

    //altijd true
    @Override
    public boolean isValid(Dice[] dice) {
        return true;
    }
}

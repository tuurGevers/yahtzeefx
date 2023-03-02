package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class FourOfAKind extends Scoring {
    //som van alle stenen
    @Override
    public int getScore(Dice[] dice) {
        int sum = 0;
        for (Dice d : dice) {
            sum += d.getValue();
        }
        return sum;
    }

    //true als de frequentie van een getal hoger is als 4
    @Override
    public boolean isValid(Dice[] dice) {
        for (int count : getFrequenty(dice).values()) {
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }
}



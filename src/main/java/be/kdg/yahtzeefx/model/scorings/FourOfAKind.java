package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

import java.util.Arrays;

public class FourOfAKind extends Scoring {
    public FourOfAKind(int id) {
        super(id);
    }

    //som van alle stenen
    @Override
    public int getScore(Dice[] dice) {
        return Arrays.stream(dice).mapToInt(Dice::getValue).sum();

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



package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

import java.util.Arrays;

public class ThreeOfAKind extends Scoring {
    public ThreeOfAKind(int id) {
        super(id);
    }

    //totaal van stenen
    @Override
    public int getScore(Dice[] dice) {
        return Arrays.stream(dice).mapToInt(Dice::getValue).sum();
    }

    //true als er eeen waarde 3 keer voorkomt
    @Override
    public boolean isValid(Dice[] dice) {
        for (int count : getFrequenty(dice).values()) {
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }
}

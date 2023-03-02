package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class ThreeOfAKind extends Scoring {
    //totaal van stenen
    @Override
    public int getScore(Dice[] dice) {
        int sum = 0;
        for (Dice d : dice) {
            sum += d.getValue();
        }
        return sum;
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

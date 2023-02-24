package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class FullHouse extends Scoring {
    @Override
    public int getScore(Dice[] dice) {
        return 25;
    }

    @Override
    public boolean isValid(Dice[] dice) {
        int threeOfAKindValue = 0;
        for (int value : getFrequenty(dice).keySet()) {
            int count = getFrequenty(dice).get(value);
            if (count == 3) {
                System.out.println("3");
                threeOfAKindValue = value;
                break;
            }
        }

        if (threeOfAKindValue != 0) {
            for (int value : getFrequenty(dice).keySet()) {
                if (value != threeOfAKindValue && getFrequenty(dice).get(value) == 2) {
                    System.out.println("2");
                    return true;
                }
            }
        }
        return false;
    }
}

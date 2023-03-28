package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class FullHouse extends Scoring {
    public FullHouse(int id) {
        super(id);
    }

    @Override
    public int getScore(Dice[] dice) {
        return 25;
    }

    @Override
    public boolean isValid(Dice[] dice) {
        //3 als er een waarde 3 keer voorkomt >3 kan niet voor full house
        int threeOfAKindValue = 0;
        for (int value : getFrequenty(dice).keySet()) {
            int count = getFrequenty(dice).get(value);
            if (count == 3) {
                threeOfAKindValue = value;
                break;
            }
        }
        //als threeof a kind een waarde heeft wordt er gechecked of een andere waarde 2 keer voorkomt
        if (threeOfAKindValue != 0) {
            for (int value : getFrequenty(dice).keySet()) {
                if (value != threeOfAKindValue && getFrequenty(dice).get(value) == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}

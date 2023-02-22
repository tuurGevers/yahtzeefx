package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class FullHouse extends Scoring {
    @Override
    protected int getScore(Dice[] dice) {

        return 0;
    }

    @Override
    protected boolean isValid(Dice[] dice) {

        return false;
    }
}

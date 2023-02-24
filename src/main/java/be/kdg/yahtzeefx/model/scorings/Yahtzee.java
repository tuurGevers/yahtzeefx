package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class Yahtzee extends Scoring {
    @Override
    public int getScore(Dice[] dice) {
        return 50;
    }

    @Override
    public boolean isValid(Dice[] dice) {
        int value = dice[0].getValue();
        for (int i = 1; i < dice.length; i++) {
            if (dice[i].getValue() != value) {
                return false;
            }
        }
        return true;
    }
}

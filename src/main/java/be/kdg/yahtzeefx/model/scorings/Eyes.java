package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class Eyes extends Scoring {
    private int eye;

    public Eyes(int eye) {
        this.eye = eye;
    }

    @Override
    protected void getScore(Dice[] dice) {

    }

    @Override
    public void isValid(Dice[] dice){

    }
}

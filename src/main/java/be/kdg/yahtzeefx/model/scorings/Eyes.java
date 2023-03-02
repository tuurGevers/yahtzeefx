package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class Eyes extends Scoring {
    private int eye;

    public Eyes(int eye) {
        this.eye = eye;
    }
    //waarde oog * aantal keer oog
    @Override
    public int getScore(Dice[] dice) {
        int count = 0;
        for (Dice d : dice) {
            if (d.getValue() == eye) {
                count++;
            }
        }
        return count*this.eye;
    }

    //altijd mogelijk
    @Override
    public boolean isValid(Dice[] dice){
        return true;
    }
}

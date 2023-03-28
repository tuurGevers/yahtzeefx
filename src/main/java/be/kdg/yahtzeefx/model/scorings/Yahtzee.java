package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

public class Yahtzee extends Scoring {
    public Yahtzee(int id) {
        super(id);
    }

    @Override
    public int getScore(Dice[] dice) {
        return 50;
    }

    //true als alle waardes dezelfde zijn
    @Override
    public boolean isValid(Dice[] dice) {
        int value = dice[0].getValue();
        //als er een waarde niet gelijk is aan de eerste word er fals gereturned
        for (int i = 1; i < dice.length; i++) {
            if (dice[i].getValue() != value) {
                return false;
            }
        }
        return true;
    }
}

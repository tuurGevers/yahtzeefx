package be.kdg.yahtzeefx.model;

import java.util.ArrayList;
import java.util.List;

public class YahtzeeModel {
    private Dice[] dice;

    public YahtzeeModel() {
        this.dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(1, false);
        }
    }

    //roll all dice
    public void rollDice() {
        for (Dice d : this.dice) {
            if(!d.isHeld()) d.roll();
        }
    }

    public Dice[] getDice() {
        return dice;
    }
}

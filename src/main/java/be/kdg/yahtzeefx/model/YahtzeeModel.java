package be.kdg.yahtzeefx.model;

import be.kdg.yahtzeefx.model.scorings.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YahtzeeModel {
    private Dice[] dice;
    public int trows = 0;
    //checkers
    Eyes eye1 = new Eyes(1);
    Eyes eye2 = new Eyes(2);
    Eyes eye3 = new Eyes(3);
    Eyes eye4 = new Eyes(4);
    Eyes eye5 = new Eyes(5);
    Eyes eye6 = new Eyes(6);


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
        trows++;
    }

    public Map<String, Integer> scores(){
        Map<String, Integer> score = new HashMap<>();
        score.put("1", eye1.getScore(this.dice));
        score.put("2", eye2.getScore(this.dice));
        score.put("3", eye3.getScore(this.dice));
        score.put("4", eye4.getScore(this.dice));
        score.put("5", eye5.getScore(this.dice));
        score.put("6", eye6.getScore(this.dice));
        return score;
    }

    public Dice[] getDice() {
        return dice;
    }
}

package be.kdg.yahtzeefx.model;

import be.kdg.yahtzeefx.model.scorings.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YahtzeeModel {
    private Dice[] dice;
    public int trows = 0;
    private List<Player> players;
    //checkers
    Eyes eye1 = new Eyes(1);
    Eyes eye2 = new Eyes(2);
    Eyes eye3 = new Eyes(3);
    Eyes eye4 = new Eyes(4);
    Eyes eye5 = new Eyes(5);
    Eyes eye6 = new Eyes(6);
    ThreeOfAKind threeOfAKind = new ThreeOfAKind();
    FourOfAKind fourOfAKind = new FourOfAKind();
    FullHouse fullHouse = new FullHouse();
    SmallStreet smallStreet = new SmallStreet();
    BigStreet bigStreet = new BigStreet();
    Yahtzee yahtzee = new Yahtzee();
    Chance chance = new Chance();

    public YahtzeeModel(List<Player> players) {
        this.dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(1, false);
        }
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    //roll all dice
    public void rollDice() {
        for (Dice d : this.dice) {
            if(!d.isHeld()) d.roll();
        }
        trows++;
    }

    public Map<String, Integer> scores() {
        Map<String, Integer> score = new HashMap<>();
        if (eye1.isValid(this.dice)) {
            score.put("1", eye1.getScore(this.dice));
        }
        if (eye2.isValid(this.dice)) {
            score.put("2", eye2.getScore(this.dice));
        }
        if (eye3.isValid(this.dice)) {
            score.put("3", eye3.getScore(this.dice));
        }
        if (eye4.isValid(this.dice)) {
            score.put("4", eye4.getScore(this.dice));
        }
        if (eye5.isValid(this.dice)) {
            score.put("5", eye5.getScore(this.dice));
        }
        if (eye6.isValid(this.dice)) {
            score.put("6", eye6.getScore(this.dice));
        }
        if (threeOfAKind.isValid(this.dice)) {
            score.put("7", threeOfAKind.getScore(this.dice));
        }
        if (fourOfAKind.isValid(this.dice)) {
            score.put("8", fourOfAKind.getScore(this.dice));
        }
        if (fullHouse.isValid(this.dice)) {
            score.put("9", fullHouse.getScore(this.dice));
        }
        if (smallStreet.isValid(this.dice)) {
            score.put("10", smallStreet.getScore(this.dice));
        }
        if (bigStreet.isValid(this.dice)) {
            score.put("11", bigStreet.getScore(this.dice));
        }
        if (yahtzee.isValid(this.dice)) {
            score.put("12", yahtzee.getScore(this.dice));
        }
        if (chance.isValid(this.dice)) {
            score.put("13", chance.getScore(this.dice));
        }
        return score;
    }


    public Dice[] getDice() {
        return dice;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

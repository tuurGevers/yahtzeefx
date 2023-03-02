package be.kdg.yahtzeefx.model;

import be.kdg.yahtzeefx.model.scorings.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YahtzeeModel {
    private Dice[] dice;
    public int trows = 0;
    private int turn = 0;
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
    private boolean finished;

    public YahtzeeModel(List<Player> players) {
        this.dice = new Dice[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new Dice(1, false);
        }
        this.players = new ArrayList<>();
        this.players.addAll(players);
        System.out.println(players);
        this.finished = false;
    }

    //roll alle stenen
    public void rollDice() {
        for (Dice d : this.dice) {
            if (!d.isHeld()) d.roll();
        }
        trows++;
    }

    //volgende beurt voor als er meerdere spelers zijn
    public void nextTurn() {
        if (turn == players.size() - 1) {
            turn = 0;
        } else {
            turn++;
        }
    }

    //alle checkers worden opgeroepen en als ze valid zijn worde de scores in een map gereturned
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

    //checkt of alle scorebladeren vol zijn
    public boolean scoreFull() {
        //kijk hoeveel spelers een volle kaart hebben
        int size = (int) players.stream().filter(p -> p.score.scores.size() == 13).count();
        //true als alle spelers hun kaarten vol heben
        return size == players.size();
    }

    public Dice[] getDice() {
        return dice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    //als een speler meer als 63 punten haalt met alleen  het bovenste deel wordt er een bonus van 35 gegeven
    public void bonusCheck(Player player) {
        int upperCount = 0;
        int upperScore = 0;
        //checkt voor waarde 1..6 of ze zijn ingevuld en telt alle waardes op
        for (int i = 1; i < 7; i++) {
            if (player.score.scores.containsKey(String.valueOf(i))) {
                upperCount++;
                upperScore += player.score.scores.get(String.valueOf(i));
            }
        }
        //als alle waarde zijn ingevuld en de score minstens 63 is en de speler de bonus nog niet heeft gekregen
        // krijgt de spler een bonus van 3
        if (upperCount == 6 && upperScore >= 63 && !player.score.upperBonus) {
            player.score.upperBonus = true;
            player.score.addScore(35);
            System.out.println("upper bonus");

        }
    }

    public Yahtzee getYahtzee() {
        return yahtzee;
    }

    public void setFinished() {
        this.finished = scoreFull();
    }

    public boolean isFinished() {
        return finished;
    }

    public Player currentPlayer() {
        return players.get(turn);
    }
}

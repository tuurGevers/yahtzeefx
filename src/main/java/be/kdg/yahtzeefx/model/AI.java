package be.kdg.yahtzeefx.model;

import java.util.HashMap;
import java.util.Map;

public class AI {
    private Player player;

    public AI() {
        this.player =new Player(3, "dummy", new Score());
    }

    private String highestKey(Map<String, Integer> scores) {
        String highest = "";
        int highscore = 0;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > highscore) {
                highscore = entry.getValue();
                highest = entry.getKey();
            }
        }
        return highest;
    }

    private void saveDice(Dice[] dice) {
        Map<Integer, Integer> frequency = new HashMap<>();

        for (Dice d : dice) {
            int value = d.getValue();
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);
        }
        for (Dice d : dice) {
            int value = d.getValue();
            d.setHeld(frequency.get(value) >= 2);
        }

    }

    private void roll(YahtzeeModel model) {
        model.rollDice();
    }

    private void selectHighest(Map<String, Integer> scores) {
        Map<String, Integer> highestPossible = new HashMap<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (!this.player.score.scores.containsKey(entry.getKey())) {
                highestPossible.put(entry.getKey(), entry.getValue());
            }
        }

        String key = highestKey(highestPossible);
        this.player.score.scores.put(key, scores.get(key));
        this.player.score.addScore(scores.get(key));
    }

    public void takeTurn(YahtzeeModel model){
        for (int i =0; i<3; i++){
            roll(model);
            saveDice(model.getDice());
            System.out.println(model.scores());
        }

        selectHighest(model.scores());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

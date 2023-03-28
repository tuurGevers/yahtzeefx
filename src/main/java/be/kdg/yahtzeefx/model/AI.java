package be.kdg.yahtzeefx.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Ai.
 */
public class AI {
    private Player player;
    private final Log logger;

    /**
     * Instantiates a new Ai.
     *
     * @param logger the logger
     */
    public AI(Log logger) {
        this.player = new Player(3, "computer", new Score());
        this.logger = logger;
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
            d.setHeld(frequency.get(value) >= 2);

        }

    }

    private void roll(YahtzeeModel model) {
        model.rollDice();
    }

    private void selectHighest(Map<String, Integer> scores, YahtzeeModel model) {
        Map<String, Integer> highestPossible = new HashMap<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (!this.player.score.scores.containsKey(entry.getKey())) {
                highestPossible.put(entry.getKey(), entry.getValue());
            }
        }

        String key = highestKey(highestPossible);
        if (key != null) {
            this.player.score.scores.put(key, scores.get(key));
            this.player.score.addScore(scores.get(key));
            System.out.println(key + ":" + scores.get(key));
        } else {
            Map<String, Integer> emptyScores = model.allScores();
            for (Map.Entry<String, Integer> entry : emptyScores.entrySet()) {
                if (!this.player.score.scores.containsKey(entry.getKey())) {
                    this.player.score.scores.put(entry.getKey(), 0);
                    break;
                }
            }

        }
    }

    /**
     * Take turn.
     *
     * @param model the model
     * @throws FileException the file exception
     * @throws IOException   the io exception
     */
    public void takeTurn(YahtzeeModel model) throws FileException, IOException {
        for (int i = 0; i < 3; i++) {
            roll(model);
            saveDice(model.getDice());
        }

        selectHighest(model.scores(), model);
        this.logger.saveAi();
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}

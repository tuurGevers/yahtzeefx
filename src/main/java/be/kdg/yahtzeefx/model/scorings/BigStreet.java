package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

import java.util.Map;

public class BigStreet extends Scoring {
    public BigStreet(int id) {
        super(id);
    }

    //40 punten waard
    @Override
    public int getScore(Dice[] dice) {
        return 40;
    }

    //true als eer vijf opeenvolgende waardes aanwezig zijn
    @Override
    public boolean isValid(Dice[] dice) {
        Map<Integer, Integer> frequenty = getFrequenty(dice);
        if (frequenty.size() >= 5) {
            for (int i = 1; i < 2; i++) {
                if (frequenty.get(i) != null && frequenty.get(i + 1) != null && frequenty.get(i + 2) != null && frequenty.get(i + 3) != null && frequenty.get(i + 4) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}

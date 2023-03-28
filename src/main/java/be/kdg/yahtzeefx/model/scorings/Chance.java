package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Chance extends Scoring {
    public Chance(int id) {
        super(id);
    }

    //totaal punten
    @Override
    public int getScore(Dice[] dice) {
        return Arrays.stream(dice).mapToInt(Dice::getValue).sum();
    }

    //altijd true
    @Override
    public boolean isValid(Dice[] dice) {
        return true;
    }
}

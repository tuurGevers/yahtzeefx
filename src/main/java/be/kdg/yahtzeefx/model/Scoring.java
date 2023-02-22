package be.kdg.yahtzeefx.model;

public abstract class Scoring {
    private String name;
    protected abstract int getScore(Dice[] dice);
    protected abstract boolean isValid(Dice[] dice);
}

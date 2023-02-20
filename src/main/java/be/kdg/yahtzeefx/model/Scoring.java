package be.kdg.yahtzeefx.model;

public abstract class Scoring {
    private String name;
    protected abstract void getScore(Dice[] dice);
    protected abstract void isValid(Dice[] dice);
}

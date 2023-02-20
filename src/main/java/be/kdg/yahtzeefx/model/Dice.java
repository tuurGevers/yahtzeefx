package be.kdg.yahtzeefx.model;

public class Dice {
    private int value;
    private boolean isHeld;

    public Dice(int value, boolean isHeld) {
        this.value = value;
        this.isHeld = isHeld;
    }

    //roll dice
    public void Roll(){}

    public int getValue() {
        return value;
    }

    //set isheld true
    public void hold(){}

    //set isheld false
    public void Release(){}
}

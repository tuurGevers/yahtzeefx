package be.kdg.yahtzeefx.model;

import java.util.Random;

public class Dice {
    private int value;
    private boolean isHeld;
    private Random rdm = new Random();
    public Dice(int value, boolean isHeld) {
        this.value = value;
        this.isHeld = isHeld;
    }

    //rol dobbelsteen
    public void roll(){
        this.value = rdm.nextInt(6-1+1) +1;
    }

    public int getValue() {
        return value;
    }

    //selecteerd/deselecteerd dobbelsteen
    public void select(){
        isHeld=!isHeld;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void setHeld(boolean held) {
        isHeld = held;
    }
}

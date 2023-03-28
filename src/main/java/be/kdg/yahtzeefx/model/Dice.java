package be.kdg.yahtzeefx.model;

import java.util.Random;

/**
 * The type Dice.
 */
public class Dice {
    private int value;
    private boolean isHeld;
    private final Random rdm = new Random();

    /**
     * Instantiates a new Dice.
     *
     * @param value  the value
     * @param isHeld the is held
     */
    public Dice(int value, boolean isHeld) {
        this.value = value;
        this.isHeld = isHeld;
    }

    /**
     * Roll.
     */
//rol dobbelsteen
    public void roll(){
        this.value = rdm.nextInt(6-1+1) +1;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Select.
     */
//selecteerd/deselecteerd dobbelsteen
    public void select(){
        isHeld=!isHeld;
    }

    /**
     * Is held boolean.
     *
     * @return the boolean
     */
    public boolean isHeld() {
        return isHeld;
    }

    /**
     * Sets held.
     *
     * @param held the held
     */
    public void setHeld(boolean held) {
        isHeld = held;
    }
}

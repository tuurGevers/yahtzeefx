package be.kdg.yahtzeefx.model;

/**
 * The type Player.
 */
public class Player {
    private final int playerId;
    private final String name;
    /**
     * The Score.
     */
    public Score score;

    /**
     * Instantiates a new Player.
     *
     * @param playerId the player id
     * @param name     the name
     * @param score    the score
     */
    public Player(int playerId, String name, Score score) {
        this.playerId = playerId;
        this.name = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return playerId;
    }
}

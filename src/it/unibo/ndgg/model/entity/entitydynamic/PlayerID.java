package it.unibo.ndgg.model.entity.entitydynamic;

/**
 * An enumeration representing the player.
 */
public enum PlayerID {

    /**
     * A {@link PlayerID} that represents the first player.
     */
    FIRST_PLAYER(0),

    /**
     * A {@link PlayerID} that represents the second player.
     */
    SECOND_PLAYER(1);

    private final int id;

    PlayerID(final int id) {
        this.id = id;
    }

    /**
     * A method that returns the player ID.
     * @return id the id of the player.
     */
    public int getID() {
        return id;
    }

}

package it.unibo.ndgg.model.entity;

/**
 * Represents the possible {@link Entity}'s type.
 */
public enum EntityType {

    /**
     * An {@link Entity} that represents one of the two players in game.
     */
    PLAYER,

    /** 
     * An {@link Entity} that represents a possible power up in game for a player.
     */
    POWERUP,

    /**
     * An {@link Entity} that represents the player's sword.
     */
    SWORD,

    /**
     * An {@link Entity} that represents the platform where all dynamics and static entity can move.
     */
    PLATFORM,

    /**
     * An {@link Entity} that represents the door that consist in a way to change room.
     */
    DOOR;

}

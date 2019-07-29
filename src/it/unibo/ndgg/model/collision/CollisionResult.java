package it.unibo.ndgg.model.collision;
/**
 * An enumeration which represent all possible outcomes of a collision from two bodies.
 */
public enum CollisionResult {
    /**
     * Represent a collision between a player and a sword that result in the player death.
     */
    PLAYERKILLED,

    /**
     * Represent a collision between a door and the player that is supposed to open it.
     */
    DOORTOUCHED,

    /**
     * Represent a collision between a player and a sword that result in the picking up of the sword.
     */
    SWORDPICKEDUP,

    /**
     * Represent a collision between a sword and a platform that result in the sword staying on the spot.
     */
    SWORDONTHEGROUND,

    /**
     * Represent a collision between a sword and a sword that result in the disarmament of a player.
     */
    PLAYERDISARMED,
    /**
     * Represent a collision between a player and a powerup that result in the application of that powerup to the player.
     */
    POWERUPAPPLIED;
}

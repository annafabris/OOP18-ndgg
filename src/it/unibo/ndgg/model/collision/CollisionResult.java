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
     * Represent a collision between a sword and a sword that result in the disarmament of a player.
     */
    PLAYERDISARMED,
}

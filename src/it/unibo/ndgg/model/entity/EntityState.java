package it.unibo.ndgg.model.entity;

/**
 * Represents all possible {@link Entity}'s state.
 *
 */
public enum EntityState {
    /**
     * It represents the state "moving left" of the {@link Entity}. 
     */
    MOVING_LEFT,

    /**
     * It represents the state "moving right" of the {@link Entity} .
     */
    MOVING_RIGHT,

    /**
     * It represents the state "jumping" of the {@link Entity}. 
     */
    JUMPING,

    /**
     * It represents the state "staying still" of the {@link Entity}.
     */
    STAYING_STILL,

    /**
     * It represents the state "dying" of the {@link Entity}.
     */
    DYING,

    /**
     * It represents the state "equipped" of the weapon .
     */
    EQUIPPED,

    /**
     * It represents the state dropping of the weapon.
     */
    DROPPING;
}

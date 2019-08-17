package it.unibo.ndgg.model.entity;

/**
 * Represents all possible {@link Entity}'s state.
 *
 */
public enum EntityState {
    /**
     * It represents the state "moving" of the {@link Entity}. 
     */
    MOVING,

    /**
     * It represents the state "jumping up" of the {@link Entity}.
     */
    JUMPING_UP,

    /**
     * It represents the state "jumping down" of the {@link Entity}.
     */
    JUMPING_DOWN,

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
    DROPPING,

    /**
     * It represents the state throwing of the weapon.
     */
    THROWING;
}

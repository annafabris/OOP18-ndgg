package it.unibo.ndgg.model.entity;

/**
 * Represents all possible {@link Entity}'s movements.
 */
public enum EntityMovement {

    /**
     * With this, the {@link Player} move left.
     */
    MOVE_LEFT,

    /**
     * With this, the {@link Player} move right.
     */
    MOVE_RIGHT,

    /**
     * With this, the {@link Player} jump.
     */
    JUMP,

    /**
     * With this, the {@link Player} stay still.
     */
    STAY_STILL,

    /**
     * With this the {@link Sword} is lying_down on the {@link Platform}.
     */
    LIE_DOWN,

    /**
     * With this the {@link Sword} is dropping by {@link Player}.
     */
    DROP,

    /**
     * With this the sword is equipped or unequipped.
     */
    EQUIP;
}

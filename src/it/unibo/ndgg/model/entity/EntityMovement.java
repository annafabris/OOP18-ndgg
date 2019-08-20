package it.unibo.ndgg.model.entity;

/**
 * Represents all possible {@link Entity}'s movements.
 */
public enum EntityMovement {

    /**
     * With this, the {@link Player} move left.
     */
    MOVE_LEFT(EntityState.MOVING, EntityDirection.LEFT),

    /**
     * With this, the {@link Player} move right.
     */
    MOVE_RIGHT(EntityState.MOVING, EntityDirection.RIGHT),

    /**
     * With this, the {@link Player} jump up right.
     */
    JUMP_UP_RIGHT(EntityState.JUMPING_UP, EntityDirection.RIGHT),

    /**
     * With this, the {@link Player} jump up left.
     */
    JUMP_UP_LEFT(EntityState.JUMPING_UP, EntityDirection.LEFT),

    /**
     * With this, the {@link Player} stay still.
     */
    STAY_STILL_RIGHT(EntityState.STAYING_STILL, EntityDirection.RIGHT),

    /**
     * With this, the {@link Player} stay still.
     */
    STAY_STILL_LEFT(EntityState.STAYING_STILL, EntityDirection.LEFT),

    /**
     * With this, the {@link Player} die.
     */
    DIE_RIGHT(EntityState.DYING, EntityDirection.RIGHT),

    /**
     * With this, the {@link Player} die.
     */
    DIE_LEFT(EntityState.DYING, EntityDirection.LEFT),

    /**
     * With this the {@link Sword} is dropping by {@link Player}.
     */
    DROP_RIGHT(EntityState.DROPPING, EntityDirection.RIGHT),

    /**
     * With this the {@link Sword} is dropping by {@link Player}.
     */
    DROP_LEFT(EntityState.DROPPING, EntityDirection.LEFT),

    /**
     * With this the {@link Sword} is throwing by {@link Player}.
     */
    THROW_RIGHT(EntityState.THROWING, EntityDirection.RIGHT),

    /**
     * With this the {@link Sword} is throwing by {@link Player}.
     */
    THROW_LEFT(EntityState.THROWING, EntityDirection.LEFT);

    private final EntityState state;
    private final EntityDirection direction;

    EntityMovement(final EntityState state, final EntityDirection direction) {
        this.state = state;
        this.direction = direction;
    }

    /**
     * Returns the {@link EntityState} the movement is associated to.
     * @return the {@link EntityState} 
     *          the state is associated to.
     */
    public EntityState getAssociatedEntityState() {
        return this.state;
    }

    /**
     * Returns the {@link EntityDirection} the movement is associated to.
     * @return the direction
     *          that is associated to this movement
     */
    public EntityDirection getAssociatedDirection() {
        return this.direction;
    }
}

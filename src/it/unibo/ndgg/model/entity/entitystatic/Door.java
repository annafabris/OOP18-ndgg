package it.unibo.ndgg.model.entity.entitystatic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.physic.body.StaticBodyProperties;


/**
 * Represents one of two doors that can be hit by one of two players in a game.
 */
public class Door extends AbstractEntity {

    private boolean isHit;
    private final Player player;

    /**
     * Creates the Door.
     * @param body the {@link StaticBodyProperties}
     * @param player the {@link Player}
     */
    public Door(final Optional<StaticBodyProperties> body, final Player player) {
        super();
        if (body.isPresent()) {
            super.setBody(Optional.ofNullable(body.get()));
        } 
        this.player = player;
        this.isHit = false;
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Player} who can open the door to change room.
     * @return the right {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
     */
    public Player getPlayerWhoCanOpen() {
        return this.player;
    }

    /**
     * Sets the door to "has been hit".
     */
    public void hit() {
        this.isHit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.DOOR;
    }

    /**
     * Returns if the {@link Door} has been hit by a {@link it.unibo.ndgg.model.entity.entitydynamic.Player}.
     * @return true if the {@link Door} has been hit
     */
    public boolean isDoorHitted() {
        return isHit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return EntityState.STAYING_STILL;
    }

    /**
     * Reset the hit status.
     */
    public void resetIsHit() {
        this.isHit = false;
    }
}

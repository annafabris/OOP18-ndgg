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
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Player} who can open the door to change room.
     * @return the right {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
     */
    public Player getPlayerWhoCanOpen() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.DOOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return EntityState.STAYING_STILL;
    }
}

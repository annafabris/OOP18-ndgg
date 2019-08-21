package it.unibo.ndgg.model.entity.entitystatic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.StaticBodyProperties;

/**
 * Represents the platform of the game.
 */
public class Platform extends AbstractEntity {

    /**
     * Creates the platform.
     * @param body 
     */
    public Platform(final Optional<StaticBodyProperties> body) {
        super();
        if (body.isPresent()) {
            super.setBody(Optional.ofNullable(body.get()));
        } 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.PLATFORM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return EntityState.STAYING_STILL;
    }
}

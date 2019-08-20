package it.unibo.ndgg.model.entity.entitystatic;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.body.StaticBodyProperties;

/**
 * Represents the platform of the game.
 */
public class Platform extends AbstractEntity {

    public Platform(StaticBodyProperties body) {
        super(body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.PLATFORM;
    }

}

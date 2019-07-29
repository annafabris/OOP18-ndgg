package it.unibo.ndgg.model.entity;

import it.unibo.ndgg.model.physic.body.SwordBodyProperties;

/**
 * It is an abstract class that represents all the type of {@link Entity}.
 */
public class Sword extends AbstractEntity {

    public Sword(final SwordBodyProperties body) {
        super(body);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.WEAPON;
    }

}

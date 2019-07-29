package it.unibo.ndgg.model.entity;

import it.unibo.ndgg.model.physic.body.SwordBodyProperties;

/**
 * Represents one of two sword that can be equipped by one of two players in a game.
 */
public class Sword extends AbstractEntity {

    private final SwordBodyProperties body;
    /**
     * Builds a new Sword using {@link SwordBodyProperties} to describe the physical part 
     *          of this entity.
     * @param body 
     *           it is the physical part {@link it.unibo.ndgg.model.physic.body.SwordBodyProperties}
     *           of the sword.
     */
    public Sword(final SwordBodyProperties body) {
        super(body);
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.WEAPON;
    }

}

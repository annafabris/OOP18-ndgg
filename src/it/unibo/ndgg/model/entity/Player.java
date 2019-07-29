package it.unibo.ndgg.model.entity;

import it.unibo.ndgg.model.physic.body.PlayerBodyProperties;

/**
 * Represents one of two players in play, it is an implementation of {@link AbstractEntity}.
 * and it can move in {@link World}
 */
public class Player extends AbstractEntity {

    private final PlayerBodyProperties body;

    /**
     * Builds a new Player using {@link PlayerBodyProperties} to describe the physical part 
     *          of this entity.
     * @param body 
     *           it is the physical part {@link it.unibo.ndgg.model.physic.body.PlayerBodyProperties}
     *           of the player.
     */
    public Player(final PlayerBodyProperties body) {
        super(body);
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

}

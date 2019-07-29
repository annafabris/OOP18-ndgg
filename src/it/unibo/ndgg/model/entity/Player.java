package it.unibo.ndgg.model.entity;

import it.unibo.ndgg.model.physic.body.PlayerBodyProperties;

/**
 * Represents one of two players in play, it is an implementation of {@link AbstractEntity}.
 * and it can move in {@link it.unibo.oop18.nidhogg.model.world.World}
 */
public class Player extends AbstractEntity {

    public Player(final PlayerBodyProperties body) {
        super(body);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

}

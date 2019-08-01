package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;

/**
 * Represents the implementation of Entity factory, it uses the {@link BodyProperties} to 
 * build a new entity: dynamic o static.
 */
public class EntityFactoryImpl implements EntityFactory {

    private final BodyPropertiesFactory body;

    /**
     * Builds the innerFactory of the {@link Entity}'s physical body.
     */
    public EntityFactoryImpl() {
        super();
        this.body = new BodyPropertiesFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player createPlayer(final Double height, final Double width, final Pair<Double, Double> position) {
        return new Player(this.body.createPlayerBodyProperties(position, width, height));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sword createSword(final Double height, final Double width, final Pair<Double, Double> position,
                              final Player player) {
        return new Sword(this.body.createSwordBodyProperties(position, width, height), player);
    }

}

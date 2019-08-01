package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;

/**
 * Represents the implementation of Entity factory, it uses the {@link BodyProperties} to 
 * build a new entity: dynamic o static.
 */
public class EntityFactoryImpl implements EntityFactory {

    private final BodyPropertiesFactory body;

    /**
     * Builds the innerFactory of the {@link Entity}'s physical body.
     * @param body
     *          it is the inner factory for the {@link PlayerBodyProperties}
     */
    public EntityFactoryImpl(final BodyPropertiesFactory body) {
        super();
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPlayer(final Double height, final Double width, final Pair<Double, Double> position) {
        return new Player(this.body.createPlayerBodyProperties(position, width, height));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createSword(final Double height, final Double width, final Pair<Double, Double> position) {
        return new Sword(this.body.createPlayerBodyProperties(position, width, height));
    }

}

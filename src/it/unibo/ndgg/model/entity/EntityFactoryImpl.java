package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;

/**
 * Represents the implementation of Entity factory, it uses the {@link BodyProperties} to 
 * build a new entity: dynamic o static.
 */
public class EntityFactoryImpl implements EntityFactory {

    private final BodyPropertiesFactory body;

    /**
     * Builds the innerFactory of the {@link Entity}'s physical body.
     * @param bodyPropertiesFactory 
     */
    public EntityFactoryImpl(final BodyPropertiesFactory bodyPropertiesFactory) {
        this.body = bodyPropertiesFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player createPlayer(final Double height, final Double width, 
                               final Pair<Double, Double> position, final EntityDirection direction) {
        return new Player(this.body.createDynamicBodyProperties(position, width, height, EntityType.PLAYER), direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sword createSword(final Double height, final Double width, final Pair<Double, Double> position,
                              final Player player, final EntityDirection direction) {
        return new Sword(this.body.createDynamicBodyProperties(position, width, height, EntityType.SWORD), player, direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform createPlatform(final Double height, final Double width, final Pair<Double, Double> position,
                                   final EntityType type) {
        return new Platform(this.body.createStaticBodyProperties(position, width, height, type));
    }

}

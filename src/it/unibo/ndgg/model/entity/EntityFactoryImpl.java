package it.unibo.ndgg.model.entity;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
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
    public Player createPlayer(final Double width, final Double height, 
                               final Pair<Double, Double> position, final EntityDirection direction) {
        return new Player(Optional.of(this.body.createDynamicBodyProperties(position, width, height, EntityType.PLAYER)), direction);
    }

    /*@Override
    public Sword createSword(final Double width, final Double height, final Pair<Double, Double> position,
                              final Player player, final EntityDirection direction) {
        return new Sword(Optional.of(this.body.createDynamicBodyProperties(position, width, height, EntityType.SWORD)), player, direction);
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    public Sword createSword(final Double width, final Double height, final Pair<Double, Double> position,
                              final Player player, final EntityDirection direction) {
        return new Sword(Optional.empty(), player, direction);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Platform createPlatform(final Double width, final Double height, final Pair<Double, Double> position) {
        return new Platform(Optional.of(this.body.createStaticBodyProperties(position, width, height, EntityType.PLATFORM)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door createDoor(final Double width, final Double height, final Pair<Double, Double> position, final Player player) {
        return new Door(Optional.of(this.body.createStaticBodyProperties(position, width, height, EntityType.DOOR)), player);
    }
}

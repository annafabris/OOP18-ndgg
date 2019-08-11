package it.unibo.ndgg.model.physic;

import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.world.World;

/**
 * A class that represent the World in which the physic of the game takes place.
 */
public class BodyPropertiesWorldImpl implements BodyPropertiesWorld {

    private final org.dyn4j.dynamics.World world;
    private final BodyAssociations bodyAssociation;
    private final World physicWorld;

    public BodyPropertiesWorldImpl(World physicWorld, org.dyn4j.dynamics.World world, BodyAssociations bodyAssociations) {
        this.world = world;
        this.physicWorld = physicWorld;
        this.bodyAssociation = bodyAssociations;
        //TODO aggiungere regole collisioni
        //this.world.addListener();
    }

    /**
     * {@inheritDoc}.
     */
    public void update() {

    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public org.dyn4j.dynamics.World getWorld() {
        return this.world;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void putPhysicalBodyToBody(final BodyProperties bodyProperties, final Body body, final EntityType type) {
        this.bodyAssociation.setBodyProperties(body, bodyProperties, type);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Player getPlayerFromBody(final Body body) {
        return this.bodyAssociation.getPlayer(body);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Sword getSwordFromBody(final Body body) {
        return this.bodyAssociation.getSword(body);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Door getDoorFromBody(final Body body) {
        return this.bodyAssociation.getDoor(body);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Platform getPlatformFromBody(final Body body) {
        return this.bodyAssociation.getPlatform(body);
    }


    /**
     * {@inheritDoc}.
     */
    @Override
    public EntityType getEntityTypeFromBody(final Body body) {
        return this.bodyAssociation.getEntityType(body);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public BodyProperties getBodyPropertiesFromBody(final Body body) {
        return this.bodyAssociation.getBodyProperties(body);
    }
}

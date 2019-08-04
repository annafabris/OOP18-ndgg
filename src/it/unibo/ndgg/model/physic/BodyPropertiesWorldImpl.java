package it.unibo.ndgg.model.physic;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * {@inheritDoc}.
 */
public class BodyPropertiesWorldImpl implements BodyPropertiesWorld {

    private final World world;
    private final BodyAssociations bodyAssociation;

    public BodyPropertiesWorldImpl(World world, BodyAssociations bodyAssociations) {
        this.world = world;
        this.bodyAssociation = bodyAssociations;
    }

    /**
     * {@inheritDoc}.
     */
    public void update() {

    }

    /**
     * {@inheritDoc}.
     */
    public World getWorld() {
        return this.world;
    }


    public void putPhysicalBodyToBody(final BodyProperties bodyProperties, final Body body, final EntityType type) {
        this.bodyAssociation.setBodyProperties(body, bodyProperties, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayerFromBody(final Body body) {
        return this.bodyAssociation.getPlayer(body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sword getSwordFromBody(final Body body) {
        return this.bodyAssociation.getSword(body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door getDoorFromBody(final Body body) {
        return this.bodyAssociation.getDoor(body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform getPlatformFromBody(final Body body) {
        return this.bodyAssociation.getPlatform(body);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getEntityTypeFromBody(final Body body) {
        return this.bodyAssociation.getEntityType(body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyProperties getBodyPropertiesFromBody(final Body body) {
        return this.bodyAssociation.getBodyProperties(body);
    }
}

package it.unibo.ndgg.model.physic;

import java.awt.Toolkit;

import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.collision.CollisionRules;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * {@inheritDoc}.
 */
public class BodyPropertiesWorldImpl implements BodyPropertiesWorld {
    /**
     * Conversion value.
     */
    public static final double NANO_TO_BASE = 1.0e9;
    private final org.dyn4j.dynamics.World world;
    private final BodyAssociations bodyAssociation;
    private long last;

    /**
     * Creates a World that manages the {link org.dyn4j.dynamics.World}.
     * @param worldImpl the {link it.unibo.ndgg.model.world.WorldImpl}
     * @param world the {link org.dyn4j.dynamics.World}
     * @param bodyAssociations the {@link BodyAssociations}
     */
    public BodyPropertiesWorldImpl(final WorldImpl worldImpl, final org.dyn4j.dynamics.World world, 
            final BodyAssociations bodyAssociations) {
        this.world = world;
        this.bodyAssociation = bodyAssociations;
        this.last = System.nanoTime();
        this.world.addListener(new CollisionRules(worldImpl, this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        Toolkit.getDefaultToolkit().sync();
        final long diff = System.nanoTime() - this.last;
        this.last = System.nanoTime();
        final double elapsedTime = diff / NANO_TO_BASE;       // convert from nanoseconds to seconds
        this.world.update(elapsedTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.dyn4j.dynamics.World getWorld() {
        return this.world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

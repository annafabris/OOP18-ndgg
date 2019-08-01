package it.unibo.ndgg.model.physic;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
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
     * Puts an association inside this PhysicalWorld between a PhysicalBody, its Body, its EntityType and its actual 
     * implementation {@link Player}.
     * @param bodyProperties {@link BodyProperties}
     * @param body {@link Body}
     * @param type {@link EntityType}
     */
    public void putPhysicalBodyToBody(final BodyProperties bodyProperties, final Body body, final EntityType type) {
        this.bodyAssociation.setBodyProperties(body, bodyProperties, type);
    }

    /**
     * Returns the {@link Player} associated to the {@link Body}.
     * @param body {@link Body}
     * @return {@link Player}
     */
    public Player getPlayerFromBody(final Body body) {
        return this.bodyAssociation.getPlayer(body);
    }

    /**
     * Returns the {@link Sword} associated to the {@link Body}.
     * @param body {@link Body}
     * @return {@link Sword}
     */
    public Sword getSwordFromBody(final Body body) {
        return this.bodyAssociation.getSword(body);
    }

    /**
     * Returns the {@link EntityType} associated to the {@link Body}.
     * @param body {@link Body}
     * @return {@link EntityType}
     */
    public EntityType getEntityTypeFromBody(final Body body) {
        return this.bodyAssociation.getEntityType(body);
    }
}

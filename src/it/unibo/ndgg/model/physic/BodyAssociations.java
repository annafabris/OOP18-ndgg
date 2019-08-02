package it.unibo.ndgg.model.physic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.physic.body.BodyProperties;

//TODO potrebbe essere classe locale a BodyPropertiesWorldImp ? ANNA
/**
 * A class that contains all the associations between the {@link Body}, {@link BodyProperties} and {@link AbstractEntity}.
 * {@link EntityType} is memorized to get the correct implementations of {@link BodyProperties}
 */
public class BodyAssociations {

    private Map<EntityType, List<AbstractEntity>> entities;
    private Map<Body, Pair<EntityType, BodyProperties>> bodyToPropertiesAssociation = new HashMap<>();

    public BodyAssociations(Map<EntityType, List<AbstractEntity>> entities) {
        this.entities = entities;
    }

    /**
     * {@inheritDoc}
     */
    public Sword getSword(final Body body) {
        return (Sword) getEntity(this.entities.get(EntityType.SWORD), getBodyProperties(body));
    }

    /**
     * {@inheritDoc}
     */
    public Player getPlayer(final Body body) {
        return (Player) getEntity(this.entities.get(EntityType.PLAYER), getBodyProperties(body));
    }

    //TODO sistemare quando ci sono le classi giuste
    /**
     * {@inheritDoc}
     */
    public Door getDoor(final Body body) {
        return null;
        //return (Door) getEntity(this.entities.get(EntityType.DOOR), bodyProperties);
    }

    /**
    * {@inheritDoc}
    */
    /*public Platform getPlayer(final Body body) {
        return (Platform) getEntity(this.entities.get(EntityType.PLATFORM), bodyProperties);
    }*/

    /**
     * {@inheritDoc}
     */
    public EntityType getEntityType(final Body body) {
        return this.bodyToPropertiesAssociation.get(body).getLeft();
    }

    /**
     * Sets one association between {@link Body}, {@link BodyProperties} and {@link EntityType}.
     * @param body {@link Body}
     * @param bodyProperties {@link BodyProperties}
     * @param entityType {@link EntityType}
     */
    public void setBodyProperties(final Body body, final BodyProperties bodyProperties, final EntityType entityType) {
        this.bodyToPropertiesAssociation.put(body, new MutablePair<>(entityType, bodyProperties));
    }

    /**
     * Returns the {@link BodyProperties} given its {@link Body}.
     * @param body {@link Body}
     * @return {@link BodyProperties}
     */
    public BodyProperties getBodyProperties(final Body body) {
        return this.bodyToPropertiesAssociation.get(body).getRight();
    }


    /**
     * Return the {@link AbstractEntity} given the entities list and the right {@link AbstractEntity}.
     * @param entity the list of all the entities (of only one type)
     * @param bodyProperties {@link BodyProperties}
     * @return {@link AbstractEntity}
     */
    private AbstractEntity getEntity(final List<AbstractEntity> entity, final BodyProperties bodyProperties) {
        entity.stream().filter(i -> i.getBody().equals(bodyProperties)).collect(Collectors.toList());
        if (entity.size() != 1) {
            throw new IllegalStateException("The right BodyProperty is not present in Sword");
        } else {
            return entity.get(0);
        }
    }
}


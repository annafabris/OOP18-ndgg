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
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * A class that contains all the associations between the {@link org.dyn4j.dynamics.Body}, 
 * {@link it.unibo.ndgg.model.physic.body.BodyProperties} and {@link it.unibo.ndgg.model.entity.AbstractEntity}.
 * {@link it.unibo.ndgg.model.entity.EntityType} is memorized to get the correct implementations of 
 * {@link it.unibo.ndgg.model.physic.body.BodyProperties}.
 */
public class BodyAssociations {

    private Map<EntityType, List<AbstractEntity>> entities;
    private Map<Body, Pair<EntityType, BodyProperties>> bodyToPropertiesAssociation = new HashMap<>();

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}.
     * @param body the {@link org.dyn4j.dynamics.Body}
     * @return the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
     */
    public Sword getSword(final Body body) {
        return (Sword) this.entities.get(EntityType.SWORD).stream()
                                                          .map(i -> (Sword) i)
                                                          .filter(i -> i.bodyProperiesExist())
                                                          .filter(i -> i.getBody().equals(this.getBodyProperties(body)))
                                                          .findFirst()
                                                          .get();
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Player}.
     * @param body the {@link org.dyn4j.dynamics.Body}
     * @return the {@link it.unibo.ndgg.model.entity.entitydynamic.Player}
     */
    public Player getPlayer(final Body body) {
        return (Player) this.entities.get(EntityType.PLAYER).stream().filter(i -> i.getBody().equals(this.getBodyProperties(body)))
                .findFirst().get();
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitystatic.Door}.
     * @param body the {@link org.dyn4j.dynamics.Body}
     * @return the {@link it.unibo.ndgg.model.entity.entitystatic.Door}
     */
    public Door getDoor(final Body body) {
        return (Door) this.entities.get(EntityType.DOOR).stream().filter(i -> i.getBody().equals(this.getBodyProperties(body)))
                .findFirst().get();
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitystatic.Platform}.
     * @param body the {@link org.dyn4j.dynamics.Body}
     * @return the {@link it.unibo.ndgg.model.entity.entitystatic.Platform}
     */
    public Platform getPlatform(final Body body) {
        return (Platform) getEntity(this.entities.get(EntityType.PLATFORM), getBodyProperties(body));
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.EntityType}.
     * @param body the {@link org.dyn4j.dynamics.Body}
     * @return the {@link it.unibo.ndgg.model.entity.EntityType}
     */
    public EntityType getEntityType(final Body body) {
        return this.bodyToPropertiesAssociation.get(body).getLeft();
    }

    /**
     * Sets one association between {@link org.dyn4j.dynamics.Body}, {@link it.unibo.ndgg.model.physic.body.BodyProperties} and 
     * {@link it.unibo.ndgg.model.entity.EntityType}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @param bodyProperties {@link it.unibo.ndgg.model.physic.body.BodyProperties}
     * @param entityType {@link it.unibo.ndgg.model.entity.EntityType}
     */
    public void setBodyProperties(final Body body, final BodyProperties bodyProperties, final EntityType entityType) {
        this.bodyToPropertiesAssociation.put(body, new MutablePair<>(entityType, bodyProperties));
    }

    /**
     * Sets the Map consisting of an association between the {@link it.unibo.ndgg.model.entity.EntityType} and a list of 
     * {@link it.unibo.ndgg.model.entity.AbstractEntity}.
     * @param entities the list of all the {@link it.unibo.ndgg.model.entity.AbstractEntity}
     */
    public void setEntities(final Map<EntityType, List<AbstractEntity>> entities) {
        this.entities = entities;
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.physic.body.BodyProperties} given its {@link org.dyn4j.dynamics.Body}.
     * @param body {@link org.dyn4j.dynamics.Body}
     * @return {@link it.unibo.ndgg.model.physic.body.BodyProperties}
     */
    public BodyProperties getBodyProperties(final Body body) {
        return this.bodyToPropertiesAssociation.get(body).getRight();
    }


    /**
     * Return the {@link it.unibo.ndgg.model.entity.AbstractEntity} given the entities list and the right 
     * {@link it.unibo.ndgg.model.entity.AbstractEntity}.
     * @param entity the list of all the entities (of only one type)
     * @param bodyProperties {@link it.unibo.ndgg.model.physic.body.BodyProperties}
     * @return {@link it.unibo.ndgg.model.entity.AbstractEntity}
     */
    private AbstractEntity getEntity(final List<AbstractEntity> entity, final BodyProperties bodyProperties) {
        List<AbstractEntity> abstractEntity = entity.stream().filter(i -> i.getBody().equals(bodyProperties)).collect(Collectors.toList());
        if (abstractEntity.size() != 1) {
            throw new IllegalStateException("The right BodyProperty is not present in Sword");
        } else {
            return entity.get(0);
        }
    }
}


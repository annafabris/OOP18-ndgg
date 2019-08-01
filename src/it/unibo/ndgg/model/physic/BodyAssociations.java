package it.unibo.ndgg.model.physic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.BodyProperties;

//TODO potrebbe essere classe locale a BodyPropertiesWorldImp ? ANNA
/**
 * A class that contains all the associations between the {@link Body} and the {@link BodyProperties}.
 * {@link EntityType} is memorized to get the correct implementations of {@link BodyProperties}
 * @param <X>
 */
public class BodyAssociations {

    private Map<Body, Pair<EntityType, BodyProperties>> association = new HashMap<>();

    /**
     * 
     * @param body
     * @return
     */
    public BodyProperties getBodyProperties(Body body) {
        return this.association.get(body).getRight();
    }
    
    /**
     * 
     * @param body
     * @return
     */
    public EntityType getEntityType(Body body) {
        return this.association.get(body).getLeft();
    }
    
    /**
     * 
     * @param body
     * @param physicalBody
     */
    public void setBodyProperties(Body body, BodyProperties bodyProperties, EntityType entityType) {
        this.association.put(body, new MutablePair<>(entityType, bodyProperties));
    }
    
}


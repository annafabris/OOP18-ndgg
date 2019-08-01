package it.unibo.ndgg.model.physic;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.BodyProperties;

public class BodyPropertiesWorldImpl implements BodyPropertiesWorld{

    private final World world;
    private final BodyAssociations bodyAssociation;
    
    public BodyPropertiesWorldImpl(World world, BodyAssociations bodyAssociation) {
        super();
        this.world = world;
        this.bodyAssociation = bodyAssociation;
    }    
    
    /**
     * Puts an association inside this PhysicalWorld between a PhysicalBody, its Body,
     *  its EntityType and its actual implementation {@link Player}.
     * @param bodyProperties
     * @param body
     * @param type
     */
    public void putPhysicalBodyToBody(final BodyProperties bodyProperties, final Body body, final EntityType type) {
        this.bodyAssociation.setBodyProperties(body, bodyProperties, type);
    }

    /**
     * Gets the {@link BodyProperties} from {@link Body}
     * @param body
     * @return
     */
    public BodyProperties getPhysicalBodyFromBody(final Body body) {
        return this.bodyAssociation.getBodyProperties(body);
    }
    
    /**
     * 
     * @param body
     * @return
     */
    public EntityType getEntityTypeFromBody(final Body body) {
        return this.bodyAssociation.getEntityType(body);
    }


}

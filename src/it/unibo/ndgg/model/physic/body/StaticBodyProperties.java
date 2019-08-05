package it.unibo.ndgg.model.physic.body;

import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.EntityState;

public class StaticBodyProperties extends AbstractBodyProperties {

    public StaticBodyProperties(Body body) {
        super(body);
    }

    @Override
    public EntityState getState() {
        return EntityState.STAYING_STILL;
    }

}

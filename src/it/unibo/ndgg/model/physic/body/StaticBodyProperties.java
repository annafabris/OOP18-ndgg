package it.unibo.ndgg.model.physic.body;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.entity.EntityState;

public class StaticBodyProperties extends AbstractBodyProperties {

    public StaticBodyProperties(final Body body) {
        super(body);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return EntityState.STAYING_STILL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getVelocity() {
        return Pair.of(0.0, 0.0);
    }

    @Override
    public Body getPhysicalBody() {
        // TODO Auto-generated method stub
        return null;
    }
}

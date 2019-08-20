package it.unibo.ndgg.model.entity;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * Represents an abstract representation of {@link Entity}.
 */
public abstract class AbstractEntity implements Entity {

    private final BodyProperties body;

    /**
     * It is a constructor for this class that require a {@link it.unibo.ndgg.model.physic.body.BodyProperties}.
     * @param body 
     *          an element of {it.unibo.ndgg.model.physic.body.BodyProperties}
     */
    public AbstractEntity(final BodyProperties body) {
        super();
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return this.body.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityState getState() {
        return this.body.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract EntityType getType();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return body.exists();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getDimension() {
        return this.body.getDimension();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyProperties getBody() {
        return this.body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (body == null) {
            if (other.body != null) {
                return false;
            }
        } else if (!body.equals(other.body)) {
            return false;
            }
        return true;
    }

}

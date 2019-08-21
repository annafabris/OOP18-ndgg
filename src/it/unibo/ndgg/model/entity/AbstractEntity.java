package it.unibo.ndgg.model.entity;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * Represents an abstract representation of {@link Entity}.
 */
public abstract class AbstractEntity implements Entity {

    private Optional<BodyProperties> body = Optional.empty();

    /**
     * It is a constructor for this class that require a {@link it.unibo.ndgg.model.physic.body.BodyProperties}.
     */
    public AbstractEntity() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void setBody(final Optional<BodyProperties> body) {
        this.body = body;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getPosition() {
        if (this.body.isPresent()) {
            return this.body.get().getPosition();
        } else {
            throw new IllegalStateException("The Body is not present");
        }
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
        if (this.body.isPresent()) {
            return this.body.get().exists();
        } else {
            throw new IllegalStateException("The Body is not present");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getDimension() {
        if (this.body.isPresent()) {
            return this.body.get().getDimension();
        } else {
            throw new IllegalStateException("The Body is not present");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyProperties getBody() {
        if (this.body.isPresent()) {
            return this.body.get();
        } else {
            throw new IllegalStateException("The Body is not present");
        }
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

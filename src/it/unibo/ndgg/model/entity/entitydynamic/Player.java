package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.PlayerBodyProperties;
import it.unibo.ndgg.model.physic.movement.MovementVectorValues;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;

/**
 * Represents one of two players in play, it is an implementation of {@link AbstractEntity}.
 * and it can move in {@link World}
 */
public class Player extends AbstractEntity {

    private final PlayerBodyProperties body;
    private Optional<Sword> sword;

    /**
     * Builds a new Player using {@link PlayerBodyProperties} to describe the physical part 
     *          of this entity.
     * @param body 
     *           it is the physical part {@link it.unibo.ndgg.model.physic.body.PlayerBodyProperties}
     *           of the player.
     */
    public Player(final PlayerBodyProperties body) {
        super(body);
        this.body = body;
        this.sword = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * This represents the act of equipping the {@link Weapon} by the player.
     * @param sword
     *          it is the sword of the player
     */
    public void equipWeapon(final Sword sword) {
        this.sword = Optional.of(sword);
    }

    /**
     * This represents the act of unequipping the {@link Weapon} by the player.
     */
    public void unequipWeapon() {
        this.sword = Optional.empty();
    }

    /**
     * Represents the change of player's state, for an input or a condition.
     * @param movement
     *          it is the {@link EntityMovement} that the player have to do
     */
    public void move(final EntityMovement movement) {
        MovementVectorValues movementValue = new MovementVectorValuesImpl();
        this.body.applyMovement(movement, movementValue.getMovementVector(movement).x, 
                                movementValue.getMovementVector(movement).y);
    }

    /**
     * Returns if presents the {@link Sword} of this player else a empty optional.
     * @return weapon
     */
    public Optional<Sword> getWeapon() {
        return this.sword;
    }

    /**
     * Represent the death of the player in a {@link Room}, not in the {@link World}.
     */
    public void die() {
        this.move(EntityMovement.DIE);
    }

}

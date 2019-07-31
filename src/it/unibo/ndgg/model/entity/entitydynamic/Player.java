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
    private Optional<Weapon> weapon;

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
        this.weapon = Optional.empty();
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
     * @throws Exception 
     *          if the player has already a weapon
     */
    public void equipWeapon(final Weapon sword) throws Exception {
        if (this.weapon.isPresent()) {
            throw new Exception("The player has already a weapon");
        } else {
            this.weapon = Optional.of(sword);
            sword.equipWeapon(this);
        }
    }

    /**
     * This represents the act of dropping the {@link Weapon} by the player.
     * @param movement 
     *          it indicates if the weapon is lose or drop
     * @throws Exception 
     *          if the player doesn't have a {@link Weapon}
     * 
     */
    public void dropWeapon(final EntityMovement movement) throws Exception {
        if (this.weapon.isPresent()) {
            this.weapon.get().unequipWeapon(movement);
            this.weapon = Optional.empty();
        } else {
            throw new Exception("The player doesn't have a weapon");
        }
    }


    /**
     * Represents the change of player's state, for an input or a condition.
     * @param movement
     *          it is the {@link EntityMovement} that the player have to do
     */
    public void move(final EntityMovement movement) {
        final MovementVectorValues movementValue = new MovementVectorValuesImpl();
        this.body.applyMovement(movement, movementValue.getMovementVector(movement).x, 
                                movementValue.getMovementVector(movement).y);
    }

    /**
     * Returns if presents the {@link Sword} of this player else a empty optional.
     * @return weapon
     */
    public Optional<Weapon> getWeapon() {
        return this.weapon;
    }

    /**
     * Represent the death of the player in a {@link Room}, not in the {@link World}.
     */
    public void die() {
        this.move(EntityMovement.DIE);
    }

}

package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.SwordBodyProperties;
import it.unibo.ndgg.model.physic.movement.MovementVectorValues;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;

/**
 * Represents one of two sword that can be equipped by one of two players in a game.
 */
public class Sword extends AbstractDynamicEntity implements Weapon  {

    private final SwordBodyProperties body;
    private Optional<Player> player;
    /**
     * Builds a new Sword using {@link SwordBodyProperties} to describe the physical part 
     *          of this entity, and a Player who equipped this.
     * @param body 
     *           it is the physical part {@link it.unibo.ndgg.model.physic.body.SwordBodyProperties}
     *           of the sword.
     * @param player
     *           it is the player @link Player} who equipped the sword;
     * @param direction
     *           it is its direction
     */
    public Sword(final SwordBodyProperties body, final Player player, final EntityDirection direction) {
        super(direction, body);
        this.body = body;
        this.player = Optional.of(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipWeapon(final Player player) throws Exception {
        if (!this.player.isPresent()) {
           this.player = Optional.of(player);
           this.move(EntityMovement.EQUIP);
        } else {
            throw new Exception("This sword is already equipped");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.SWORD;
    }

    /**
     * {@inheritDoc}
     * @throws Exception 
     */
    @Override
    public void unequipWeapon(final EntityMovement movement) throws Exception {
        if (this.player.isPresent()) {
            this.player = Optional.empty();
            this.move(movement);
        } else {
            throw new Exception("This sword is not equipped");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final EntityMovement movement) {
        final MovementVectorValues movementValue = new MovementVectorValuesImpl();
        this.body.applyMovement(movement, movementValue.getMovementVector(movement).x, 
                                movementValue.getMovementVector(movement).y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Player> getPlayer() {
        return this.player;
    }

}

package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.movement.MovementVectorValues;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;

/**
 * Represents one of two sword that can be equipped by one of two players in a game.
 */
public class Sword extends AbstractDynamicEntity implements Weapon  {

    private Optional<Player> player = Optional.empty();

    /**
     * Builds a new Sword using {@link DynamicBodyProperties} to describe the physical part 
     *          of this entity, and a Player who equipped this.
     * @param body 
     *           it is the physical part {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     *           of the sword.
     * @param player
     *           it is the player @link Player} who equipped the sword;
     * @param direction
     *           it is its direction
     */
    public Sword(final Optional<DynamicBodyProperties> body, final Player player, final EntityDirection direction) {
        super(direction, body);
        player.equipWeapon(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipWeapon(final Player player) throws Exception {
        if (!this.player.isPresent()) {
           this.player = Optional.of(player);
           this.setBody(Optional.empty());
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
    public Optional<Player> getPlayer() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final EntityMovement movement) {
        if (movement.getAssociatedEntityState() == EntityState.THROWING) {
            final MovementVectorValues movementValue = new MovementVectorValuesImpl();
            final Vector2 vector = movementValue.getMovementVector(movement);
            ((DynamicBodyProperties) this.getBody()).applyImpulse(movement, vector.x, vector.y);
        }
        if(this.bodyProperiesExist()) {
            this.changeEntityState(movement.getAssociatedEntityState());
        }
        this.setCurrentDirection(movement.getAssociatedDirection());
    }

    /**
     * Returns true if the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} is not Optional.empty().
     * @return true if the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} exist
     */
    public boolean bodyProperiesExist() {
        try {
            super.getBody();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Add a {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}.
     * @param body {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     */
    public void addBodyProperties(final DynamicBodyProperties body) {
        super.setDynamicBody(Optional.of(body));
    }

    /**
     * Remove the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}.
     */
    public void removeBodyProperties() {
        super.setDynamicBody(Optional.empty());
    }
}

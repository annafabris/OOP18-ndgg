package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.movement.MovementVectorValues;
import it.unibo.ndgg.model.physic.movement.MovementVectorValuesImpl;
import it.unibo.ndgg.view.entitydraw.dynamic.SoundsTypes;

/**
 * Represents one of two players in play, it is an implementation of {@link AbstractEntity}.
 * and it can move in {@link World}
 */
public class Player extends AbstractDynamicEntity {

    private Optional<Weapon> weapon;
    private Optional<SwordGuard> typeOfGuard;

    /**
     * Builds a new Player using {@link DynamicBodyProperties} to describe the physical part 
     *          of this entity.
     * @param body 
     *           it is the physical part {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties}
     *           of the player.
     * @param direction
     *          it is the first {@link EntityDirection} of the player
     */
    public Player(final DynamicBodyProperties body, final EntityDirection direction) {
        super(direction, body);
        this.weapon = Optional.empty();
        this.typeOfGuard = Optional.empty();
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
    public void equipWeapon(final Weapon sword) {
        if (!this.weapon.isPresent()) {
            try {
                sword.equipWeapon(this);
            } catch (Exception e) {
                System.out.println("The player has already a sword");
                e.printStackTrace();
            }
            this.weapon = Optional.of(sword);
            this.typeOfGuard = Optional.of(SwordGuard.LOW);
        }
    }

    /**
     * This represents the act of dropping the {@link Weapon} by the player.
     * @param movement 
     *          it indicates if the weapon is lose or drop
     */
    public void dropWeapon(final EntityMovement movement) {
        if (this.weapon.isPresent()) {
            try {
                this.weapon.get().unequipWeapon(movement);
            } catch (Exception e) {
                System.out.println("The player hasn't a sword");
                e.printStackTrace();
            }
            this.weapon = Optional.empty();
            this.typeOfGuard = Optional.empty();
        }
    }

    /**
     * Returns if presents the {@link Sword} of this player else a empty optional.
     * @return weapon
     */
    public Optional<Weapon> getWeapon() {
        return this.weapon;
    }

    /**
     * Return the current sword guard of the player.
     * @return
     *          the current sword guard if is present
     */
    public Optional<SwordGuard> getSwordGuard() {
            return this.typeOfGuard;
    }

    /**
     * It changes the sword guard of the player is staying still.
     */
    public void changeGuard() {
        if (this.getState() == EntityState.STAYING_STILL && this.typeOfGuard.isPresent()) {
            Body swordBody = ((Sword) this.weapon.get()).getBody().getPhysicalBody();
          //TODO riguardarci
            swordBody.setGravityScale(0);
            if (this.typeOfGuard.get() == SwordGuard.HIGH) {
                swordBody.translate(new Vector2(0.0,-0.5));
                this.typeOfGuard = Optional.of(SwordGuard.LOW);
            } else {
                swordBody.translate(new Vector2(0.0,-0.5));
                this.typeOfGuard = Optional.of(SwordGuard.HIGH);
            }
        }
    }

    /**
     * Represent the death of the player in a {@link Room}, not in the {@link World}.
     * 
     */
    public void die() {
        if (this.getCurrentDirection() == EntityDirection.RIGHT) {
            if (this.weapon.isPresent()) {
               this.dropWeapon(EntityMovement.DROP_RIGHT); 
            }
            this.move(EntityMovement.DIE_RIGHT);
        } else {
            if (this.weapon.isPresent()) {
                this.dropWeapon(EntityMovement.DROP_LEFT); 
            }
            this.move(EntityMovement.DIE_LEFT);
        }
        SoundsTypes.PLAYERKILLED.getSound().play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final EntityMovement movement) {
        if ((movement.getAssociatedEntityState() != EntityState.MOVING 
                && movement.getAssociatedDirection() == this.getCurrentDirection())
                || movement.getAssociatedEntityState() == EntityState.MOVING) {

            final MovementVectorValues movementValue = new MovementVectorValuesImpl();
            Vector2 values = movementValue.getMovementVector(movement);
            /*if (this.weapon.isPresent()) {
                Body swordBody = ((Sword) this.weapon.get()).getBody().getPhysicalBody();
                swordBody.translateToOrigin();
                if (movement.getAssociatedDirection() == EntityDirection.RIGHT) {
                    swordBody.shift(new Vector2(this.getPosition().getLeft() + 0.4, this.getPosition().getRight()));
                } else {
                    swordBody.shift(new Vector2(this.getPosition().getLeft() - 0.4, this.getPosition().getRight()));
                }
            }*/
            this.setCurrentDirection(movement.getAssociatedDirection());
            ((DynamicBodyProperties) super.getBody()).applyImpulse(movement, values.x, values.y);
        }
    }

}

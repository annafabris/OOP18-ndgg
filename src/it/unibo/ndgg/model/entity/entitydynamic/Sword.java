package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.SwordBodyProperties;

/**
 * Represents one of two sword that can be equipped by one of two players in a game.
 */
public class Sword extends AbstractEntity implements Weapon  {

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
     */
    public Sword(final SwordBodyProperties body, final Player player) {
        super(body);
        this.body = body;
        this.player = Optional.of(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void equipWeapon(final Player player) {
        if (!this.player.isPresent()) {
           this.player = Optional.of(player);
           this.move(EntityMovement.EQUIP);
        } else {
            //return exception
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return EntityType.WEAPON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unequipWeapon() {
        if (this.player.isPresent()) {
            this.player = Optional.empty();
            this.move(EntityMovement.DROP);
        } else {
            //exception
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final EntityMovement movement) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Player> getPlayer() {
        return this.player;
    }

}

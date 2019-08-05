package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * Represents all type of weapon that can be equipped by the players.
 */
public interface Weapon {

    /**
     * When the {@link Player} picks up the weapon.
     * @param player 
     *          it is the {@link Player} who picks up the weapon
     * @throws Exception 
     *          if the weapon is already equipped
     */
    void equipWeapon(Player player) throws Exception;

    /**
     * When the {@link Player} lose or drop the weapon.
     * @param movement
     *          it is the action of lose or drop the weapon
     * @throws Exception 
     */
    void unequipWeapon(EntityMovement movement) throws Exception;

    /**
     * Set the state of the weapon.
     * @param state
     *          the {@link EntityState} of the weapon
     */
    void changeWeaponState(EntityState state);

    /**
     * Returns the player who uses this weapon.
     * @return player
     *          it is the {@link Player} who equip this weapon if exits else return a 
     *          empty optional
     */
    Optional<Player> getPlayer();

}

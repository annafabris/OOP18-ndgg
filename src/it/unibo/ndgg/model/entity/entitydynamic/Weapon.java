package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.EntityMovement;

/**
 * Represents all type of weapon that can be equipped by the players.
 */
public interface Weapon {

    /**
     * When the {@link Player} picks up the weapon.
     * @param player 
     *          it is the {@link Player} who picks up the weapon
     */
    void equipWeapon(Player player);

    /**
     * When the {@link Player} lose the weapon.
     */
    void unequipWeapon();

    /**
     * This represents the move of the {@link PlayerBodyProperties} in the World.
     * @param movement 
     *          it represents the change of state, that it's represented by the class 
     *          {@link EntityMovement}
     */
    void move(EntityMovement movement);

    /**
     * Returns the player who uses this weapon.
     * @return player
     *          it is the {@link Player} who equip this weapon if exits else return a 
     *          empty optional
     */
    Optional<Player> getPlayer();

}

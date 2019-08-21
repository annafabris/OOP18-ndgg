package it.unibo.ndgg.model.entity.entitydynamic;

import java.util.Optional;

import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.physic.body.BodyProperties;

/**
 * Represents all type of weapon that can be equipped by the players.
 */
public interface Weapon extends DynamicEntity {

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
     * Returns the player who uses this weapon.
     * @return player
     *          it is the {@link Player} who equip this weapon if exits else return a 
     *          empty optional
     */
    Optional<Player> getPlayer();

}

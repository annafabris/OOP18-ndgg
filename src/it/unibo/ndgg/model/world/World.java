package it.unibo.ndgg.model.world;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.collision.CollisionResult;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;

/**
 * A class that represents the game World and a list of {@link Room} in which the game takes place.
 */
public interface World {

    /**
     * Initialize all the {@link it.unibo.ndgg.model.entity}s.
     */
    void start();

    /**
     * Updates all the {@link it.unibo.ndgg.model.entity} in the {@link World}.
     */
    void update();

    /**
     * Returns true if the Room has just been changed.
     */
    boolean changedRoom();
    
    int getCurrentRoom();

    /**
     * A method that gets called each time a valid collision happens.
     * @param collisionResult {@link CollisionResult}.
     * @param player the player who collide.
     * @param sword the sword which may collide with the player.
     */
    void notifyCollision(CollisionResult collisionResult, Player player, Optional<Sword> sword);

    /**
     * Returns the current {@link it.unibo.ndgg.model.GameState}.
     * @return {@link it.unibo.ndgg.model.GameState}
     */
    GameState getCurrentGameState();

    /**
     * Returns the all the {@link it.unibo.ndgg.model.entity}s.
     * @return the entities
     */
    Map<EntityType, List<AbstractEntity>> getEntities();

    /**
     * Change the player guard.
     * @param player the {@link PlayerID} of the player.
     */
    void changeGuard(PlayerID player);
    /**
     * Make the player jump.
     * @param player the {@link PlayerID} of the player.
     */
    void jumpPlayer(PlayerID player);
    /**
     * Move the player left.
     * @param player the {@link PlayerID} of the player.
     */
    void movePlayerLeft(PlayerID player);
    /**
     * Move the player right.
     * @param player the {@link PlayerID} of the player.
     */
    void movePlayerRight(PlayerID player);
    /**
     * Make the player throw his sword.
     * @param player the {@link PlayerID} of the player.
     */
    void throwSword(PlayerID player);
    /**
     * Make the player attack.
     * @param player the {@link PlayerID} of the player.
     */
    void attackPlayer(PlayerID player);


}

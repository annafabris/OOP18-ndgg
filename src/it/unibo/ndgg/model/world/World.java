package it.unibo.ndgg.model.world;

import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.collision.CollisionResult;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
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
     * A method that gets called each time a valid collision happens.
     * @param collisionResult {@link CollisionResult}
     */
    void notifyCollision(CollisionResult collisionResult);

    /**
     * Returns the current {@link it.unibo.ndgg.model.GameState}.
     * @return {@link it.unibo.ndgg.model.GameState}
     */
    GameState getCurrentGameState();

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Player}.
     * @param playerId the Id of the Sword Player
     * @return the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
     */
    Player getPlayer(int playerId);

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}.
     * @param swordId the Id of the Sword
     * @return the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}
     */
    Sword getSword(int swordId);

    /**
     * Makes the player center of mass move if necessary.
     * @param movement the {@link it.unibo.ndgg.model.entity.EntityMovement}
     * @param playerId the Id of the Player
     */
    void movePlayer(EntityMovement movement, int playerId);

    /**
     * Makes the sword center of mass move if necessary.
     * @param movement the {@link it.unibo.ndgg.model.entity.EntityMovement}
     * @paramsSwordId the Id of the Player associated to the sword to move
     */
    void moveSword(EntityMovement movement, int swordId);


}

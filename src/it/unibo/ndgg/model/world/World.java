package it.unibo.ndgg.model.world;

import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.collision.CollisionResult;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;

/**
 * A class that represents a list of {@link Room} in which the game takes place.
 */
public interface World {

    /**
     * Initialize all the {@link Entity} and ....
     */
    void start();

    /**
     * Updates all the {@link Entity} in the {@link World}.
     */
    void update();

    /**
     * A method that gets called each time a valid collision happens.
     * @param collisionResult {@link CollisionResult}
     */
    void notifyCollision(CollisionResult collisionResult);

    GameState getCurrentGameState();

    Player getPlayer(final int PlayerId);

    /**
     * 
     * @param SwordId
     * @return
     */
    Sword getSword(final int SwordId);

    void movePlayer(EntityMovement movement, int PlayerId);
    
    void throwSword(EntityMovement movement, int SwordId);


}

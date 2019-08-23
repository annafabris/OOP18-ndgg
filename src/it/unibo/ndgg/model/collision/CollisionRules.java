package it.unibo.ndgg.model.collision;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.CollisionAdapter;
import org.dyn4j.dynamics.contact.ContactConstraint;

import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitydynamic.SwordGuard;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * Represents a collision listener for physical collision between bodies used by the dyn4j library.
 * This class rules those collision.
 */
public class CollisionRules extends CollisionAdapter {

    private final WorldImpl world;
    private final BodyPropertiesWorld worldProperties;

    /**
     * Generate the CollisionRules.
     * @param world the {@link it.unibo.ndgg.model.world.WorldImpl}
     * @param worldProperties the {@link it.unibo.ndgg.model.physic.BodyPropertiesWorld}
     */
    public CollisionRules(final WorldImpl world, final BodyPropertiesWorld worldProperties) {
        super();
        this.world = world;
        this.worldProperties = worldProperties;
    }

    /**
     * The collision who needs to be managed are the ones between a {@link Player} and a {@link Sword} 
     * or a {@link Player} and a {@link Door} or a {@link Sword} and a {@link Sword} or a {@link Sword} 
     * and a {@link Platform}.
     * @param contactConstraint
     *          it is the point of collision and it represent the two bodies of the collision
     * @return 
     *          true if the collision is important otherwise false
     */
    public boolean collision(final ContactConstraint contactConstraint) {
        final Body firstBody = contactConstraint.getBody1();
        final Body secondBody = contactConstraint.getBody2();

        try {
            final Triple<Body, BodyProperties, EntityType> firstTriple = new ImmutableTriple<>(
                    firstBody, this.worldProperties.getBodyPropertiesFromBody(firstBody), 
                    this.worldProperties.getEntityTypeFromBody(firstBody));

            final Triple<Body, BodyProperties, EntityType> secondTriple;

                secondTriple = new ImmutableTriple<>(
                        secondBody, this.worldProperties.getBodyPropertiesFromBody(secondBody), 
                        this.worldProperties.getEntityTypeFromBody(secondBody));

            if ((firstTriple.getRight() == EntityType.PLAYER && secondTriple.getRight() == EntityType.DOOR)
                    || (firstTriple.getRight() == EntityType.DOOR && secondTriple.getRight() == EntityType.PLAYER)) {
                final Player player;
                final Door door;
                if (firstTriple.getRight() == EntityType.PLAYER) {
                    player = this.worldProperties.getPlayerFromBody(firstTriple.getLeft());
                    door = this.worldProperties.getDoorFromBody(secondTriple.getLeft());
                 } else {
                     player = this.worldProperties.getPlayerFromBody(secondTriple.getLeft());
                     door = this.worldProperties.getDoorFromBody(firstTriple.getLeft());
                     }
                 return this.processPlayerDoorCollision(player, door);
                } else if ((firstTriple.getRight() == EntityType.PLAYER && secondTriple.getRight() == EntityType.SWORD) 
                          || (firstTriple.getRight() == EntityType.SWORD && secondTriple.getRight() == EntityType.PLAYER)) {
                    final Player player;
                    final Sword sword;
                    if (firstTriple.getRight() == EntityType.PLAYER) {
                        player = this.worldProperties.getPlayerFromBody(firstTriple.getLeft());
                        sword = this.worldProperties.getSwordFromBody(secondTriple.getLeft());
                    } else {
                        player = this.worldProperties.getPlayerFromBody(secondTriple.getLeft());
                        sword = this.worldProperties.getSwordFromBody(firstTriple.getLeft());
                    }
                    return this.processPlayerSwordCollision(player, sword);
                } else if (firstTriple.getRight() == EntityType.PLATFORM && secondTriple.getRight() == EntityType.SWORD 
                        || firstTriple.getRight() == EntityType.SWORD && secondTriple.getRight() == EntityType.PLATFORM) {
                    final Sword sword;
                    if (firstTriple.getRight() == EntityType.PLATFORM) {
                        sword = this.worldProperties.getSwordFromBody(secondTriple.getLeft());
                    } else {
                        sword = this.worldProperties.getSwordFromBody(firstTriple.getLeft());
                    }
                    return this.processSwordPlatformCollision(sword);
                }
                return true; 
            } catch (Exception e) {
                return false;
            }
     }

    /**
     * This method process the collision between a Sword and a Platform those collision terminate with only one type o outcome.
     * @param sword 
     *          the sword who collides with the platform
     * @return 
     *          true if a collision which need to be rule happens false otherwise.
     */
    private boolean processSwordPlatformCollision(final Sword sword) {
        sword.changeEntityState(EntityState.STAYING_STILL);
        return true;
    }

    /**
     * This method process the collision between a Player and a Door those collision can terminate with different outcomes.
     * The type of the outcome depends on whether the player is the one whose supposed to open that door or not.
     * @param player
     *          the player who collides with the door
     * @param door
     *          the door which collides with the player
     * @return
     *          true if a collision which need to be rule happens false otherwise
     */
    private boolean processPlayerDoorCollision(final Player player, final Door door) {
        if (door.getPlayerWhoCanOpen() == player) {
            this.world.notifyCollision(CollisionResult.DOORTOUCHED, player, Optional.empty());
            return true;
        }
        return true;
    }

    /**
     * This method process the collision between a Player and a Sword those collision can terminate with different outcomes.
     * The type of the outcome depends on the type of the collision between the player and the sword in fact the player collides
     * with the sword in various situations such as when the attacked player collide with the sword held (or toss) by the the attacking player
     * or when a player step on a sword on the ground.
     *
     * @param player
     *          the player who collides with the sword
     * @param sword
     *          the sword which collides with the player
     * @return 
     *          true if a collision which need to be rule happens false otherwise.
     * @throws Exception 
     */
    private boolean processPlayerSwordCollision(final Player player, final Sword sword) {
        if (sword.getState() == EntityState.THROWING) {
            if (player.getWeapon().isPresent()) {
                if (player.getSwordGuard().get() != SwordGuard.HIGH) {
                    player.die(); 
                    this.world.notifyCollision(CollisionResult.PLAYERKILLED, player, Optional.of(sword));
                }
            } else {
                player.die();
                this.world.notifyCollision(CollisionResult.PLAYERKILLED, player, Optional.of(sword));
            }
        } else if (sword.getState() == EntityState.STAYING_STILL && !player.getWeapon().isPresent()) {
            this.world.notifyCollision(CollisionResult.SWORDPICKEDUP, player, Optional.of(sword));
        } 
        return true;
    }
}

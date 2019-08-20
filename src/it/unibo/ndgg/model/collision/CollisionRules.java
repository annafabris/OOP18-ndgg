package it.unibo.ndgg.model.collision;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.CollisionAdapter;
import org.dyn4j.dynamics.contact.ContactConstraint;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.physic.BodyPropertiesWorldImpl;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * Represents a collision listener for physical collision between bodies used by the dyn4j library.
 * This class rules those collision.
 */
public class CollisionRules extends CollisionAdapter {

    private boolean isCollisionRulesAlreadyCreated = false;
    private static final String COLLISION_ALREADY_CREATED_ERR = "COLLISION RULES ALREADY CREATED ERR";
    private WorldImpl outerWorld;
    private BodyPropertiesWorld worldProperties; //TODO interfaccia?

    public CollisionRules(final WorldImpl outerWorld, final BodyPropertiesWorld worldProperties) {
        super();
        this.checkIfFirstIstance(isCollisionRulesAlreadyCreated, COLLISION_ALREADY_CREATED_ERR);
        this.isCollisionRulesAlreadyCreated = true;
        this.outerWorld = outerWorld;
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

        final Triple<Body, BodyProperties, EntityType> firstTriple = new ImmutableTriple<>(
                firstBody, this.worldProperties.getBodyPropertiesFromBody(firstBody), 
                this.worldProperties.getEntityTypeFromBody(firstBody));

        final Triple<Body, BodyProperties, EntityType> secondTriple = new ImmutableTriple<>(
                secondBody, this.worldProperties.getBodyPropertiesFromBody(secondBody), 
                this.worldProperties.getEntityTypeFromBody(secondBody));
        /*
        final Vector2 point = contactConstraint.getContacts().get(0).getPoint(); punto di contatto tra le due enitità per ora non serve.
         */
        if (firstTriple.getRight() == EntityType.PLAYER && secondTriple.getRight() == EntityType.DOOR 
            || firstTriple.getRight() == EntityType.DOOR && secondTriple.getRight() == EntityType.PLAYER) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
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
                  || (firstTriple.getRight() == EntityType.SWORD && secondTriple.getRight() == EntityType.PLAYER)) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
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
                || firstTriple.getRight() == EntityType.SWORD && secondTriple.getRight() == EntityType.PLATFORM) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
            final Platform platform;
            final Sword sword;
            if (firstTriple.getRight() == EntityType.PLATFORM) {
                platform = this.worldProperties.getPlatformFromBody(firstTriple.getLeft());
                sword = this.worldProperties.getSwordFromBody(secondTriple.getLeft());
            } else {
                platform = this.worldProperties.getPlatformFromBody(secondTriple.getLeft());
                sword = this.worldProperties.getSwordFromBody(firstTriple.getLeft());
            }
            return this.processSwordPlatformCollision(sword, platform);
        } else if (firstTriple.getRight() == EntityType.SWORD && secondTriple.getRight() == EntityType.SWORD) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
            final Sword sword1;
            final Sword sword2;
            sword1 = this.worldProperties.getSwordFromBody(firstTriple.getLeft());
            sword2 = this.worldProperties.getSwordFromBody(secondTriple.getLeft());
            return this.processSwordSwordCollision(sword1, sword2);
    }
        return true;
    }

    /**
     * Method used to set both the new {@link BodyPropertiesWorldImpl} and {@link WorldImpl} in the moment in which it is changed.
     * @param newPhysicalWorld {@link BodyPropertiesWorldImpl} to be set 
     * @param newOuterWorld {@link WorldImpl} to be set
     */ 
    public void changedObservedRoom(final BodyPropertiesWorldImpl newPhysicalWorld, final WorldImpl newOuterWorld){
        setOuterWorld(newOuterWorld);
        setWorldProperties(newPhysicalWorld);
    }

    /**
     * Method used to set the new {@link BodyPropertiesWorldImpl}.
     * @param newWorldProperties -> newWorldProperties to be set
     */
    private void setWorldProperties(final BodyPropertiesWorldImpl newWorldProperties) {
        this.worldProperties = newWorldProperties;
    }

    /**
     * Method used to set the new {@link WorldImpl}.
     * @param newOuterWorld -> newOuterWorld to be set 
     */
    private void setOuterWorld(final WorldImpl newOuterWorld) {
        this.outerWorld = newOuterWorld;
    }


    /**
     * This method process the collision between a Sword and a Sword those collision terminate with only one type o outcome.
     * @param sword1 
     *          the "first" sword who collides with the second
     * @param sword2 
     *          the "second" sword which collides with the first
     * @return 
     *          True if a collision which need to be rule happens false otherwise.
     */
    private boolean processSwordSwordCollision(final Sword sword1, final Sword sword2) {
        if (sword1.getPlayer().isPresent() && sword2.getPlayer().isPresent()) {
            if ((sword1.getPosition().getRight() < sword2.getPosition().getRight())) {
                try {
                    if (sword1.getCurrentDirection() == EntityDirection.LEFT) {
                        sword1.unequipWeapon(EntityMovement.DROP_LEFT);
                    } else {
                        sword1.unequipWeapon(EntityMovement.DROP_RIGHT);
                    }
                } catch (Exception e) {
                    System.out.println("The player hasn't  a sword");
                    e.printStackTrace();
                }
                this.outerWorld.notifyCollision(CollisionResult.PLAYERDISARMED);
                return true;
            } else if ((sword2.getPosition().getRight() < sword1.getPosition().getRight())) {
                try {
                    if (sword2.getCurrentDirection() == EntityDirection.LEFT) {
                        sword2.unequipWeapon(EntityMovement.DROP_LEFT);
                    } else {
                        sword2.unequipWeapon(EntityMovement.DROP_RIGHT);
                    }
                } catch (Exception e) {
                    System.out.println("The player hasn't  a sword");
                    e.printStackTrace();
                }
                this.outerWorld.notifyCollision(CollisionResult.PLAYERDISARMED);
                return true;
            }
        }
        return false;
    }

    /**
     * This method process the collision between a Sword and a Platform those collision terminate with only one type o outcome.
     * @param sword 
     *          the sword who collides with the platform
     * @param platform
     *          the platform which collides with the sword
     * @return 
     *          true if a collision which need to be rule happens false otherwise.
     */
    private boolean processSwordPlatformCollision(final Sword sword, final Platform platform) {
        this.outerWorld.notifyCollision(CollisionResult.SWORDONTHEGROUND);
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
            door.hit();
            this.outerWorld.notifyCollision(CollisionResult.DOORTOUCHED);
            return true;
        }
        return false;
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
        if (sword.getState() == EntityState.EQUIPPED && sword.getPlayer().get() != player) {
            player.die(); 
            this.outerWorld.notifyCollision(CollisionResult.PLAYERKILLED);
            return true;
        } else if (sword.getState() == EntityState.STAYING_STILL && !player.getWeapon().isPresent()) {
            try {
                player.equipWeapon(sword);
            } catch (Exception e) {
                System.out.println("The player has already a sword");
                e.printStackTrace();
            }
            this.outerWorld.notifyCollision(CollisionResult.SWORDPICKEDUP);
            return true;
            } else if (sword.getState() == EntityState.DROPPING) {
                player.die();
                this.outerWorld.notifyCollision(CollisionResult.PLAYERKILLED);
                return true;
            }
            return false;
    }

    private void checkIfFirstIstance(final boolean condition, final String err) {
        if (condition) {
            throw new IllegalStateException(err);
        }
    }
}

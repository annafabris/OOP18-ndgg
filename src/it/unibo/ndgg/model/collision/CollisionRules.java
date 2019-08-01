package it.unibo.ndgg.model.collision;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.CollisionAdapter;
import org.dyn4j.dynamics.contact.ContactConstraint;

import it.unibo.ndgg.model.entity.Entity;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.physic.BodyPropertiesWorldImpl;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.oop18.nidhogg.model.collision.CollisionResult;

/**
 * Represents a collision listener for physical collision between bodies used by the dyn4j library.
 * This class rules those collision.
 */
public class CollisionRules extends CollisionAdapter {
	private boolean isCollisionRulesAlreadyCreated = false;
    private static String COLLISION_ALREADY_CREATED_ERR = "COLLISION RULES ALREADY CREATED ERR";
    private final WorldImpl outerWorld;
    private final BodyPropertiesWorldImpl worldProperties; //TODO interfaccia?

    CollisionRules(final WorldImpl outerWorld, final BodyPropertiesWorldImpl worldProperties) {
        super();
        this.checkIfFirstIstance(isCollisionRulesAlreadyCreated, COLLISION_ALREADY_CREATED_ERR);
        this.isCollisionRulesAlreadyCreated = true;
        this.outerWorld = outerWorld;
        this.worldProperties = worldProperties;
    }

    /**
     * @param
     * @return
     */
    public boolean collision(final ContactConstraint contactConstraint) {
		final Body firstBody = contactConstraint.getBody1();
		final Body secondBody = contactConstraint.getBody2();

		final Triple<Body, BodyProperties, EntityType> firstTriple = new ImmutableTriple<>(
					firstBody, this.worldProperties.getPhysicalBodyFromBody(firstBody), 
					this.worldProperties.getEntityTypeFromBody(firstBody));

		final Triple<Body, BodyProperties, EntityType> secondTriple = new ImmutableTriple<>(
					secondBody, this.worldProperties.getPhysicalBodyFromBody(secondBody), 
					this.worldProperties.getEntityTypeFromBody(secondBody));
		/*
		final Vector2 point = contactConstraint.getContacts().get(0).getPoint(); punto di contatto tra le due enitità per ora non serve.
		*/
		if (firstTriple.getRight() == EntityType.PLAYER && secondTriple.getRight() == EntityType.DOOR 
			|| firstTriple.getRight() == EntityType.DOOR && secondTriple.getRight() == EntityType.PLAYER) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
			final Player player;
			final Door door;
			if (firstTriple.getRight() == EntityType.PLAYER) {
				player = this.worldProperties.getPlayerFromBody(Pair.of(firstTriple.getLeft(), firstTriple.getRight()));
				door = this.worldProperties.getDoorFromBody(Pair.of(secondTriple.getLeft(), secondTriple.getRight()));
			} else {
				player = this.worldProperties.getPlayerFromBody(Pair.of(secondTriple.getLeft(), secondTriple.getRight()));
				door = this.worldProperties.getDoorFromBody(Pair.of(firstTriple.getLeft(), firstTriple.getRight()));
			}
	         return this.processPlayerDoorCollision(player, door);
		} else if (firstTriple.getRight() == EntityType.PLAYER && secondTriple.getRight() == EntityType.SWORD ||
				firstTriple.getRight() == EntityType.SWORD && secondTriple.getRight() == EntityType.PLAYER) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
			final Player player;
			final Sword sword;
			if (firstTriple.getRight() == EntityType.PLAYER) {
				player = this.worldProperties.getPlayerFromBody(new Pair<>(firstTriple.getLeft(), firstTriple.getRight()));
				sword = this.worldProperties.getSwordFromBody(new Pair<>(secondTriple.getLeft(), secondTriple.getRight()));
			} else {
				player = this.worldProperties.getPlayerFromBody(new Pair<>(secondTriple.getLeft(), secondTriple.getRight()));
				sword = this.worldProperties.getSwordFromBody(new Pair<>(firstTriple.getLeft(), firstTriple.getRight()));
			}
	         return this.processPlayerSwordCollision(player, sword);
		}
    }

    /**
     * This method process the collision between a Player and a Door those collision can terminate with different outcomes.
     * The type of the outcome depends on whether the player is the one whose supposed to open that door or not.
     * @param player
     * 			the player who collides with the door
     * @param door
     * 			the door which collides with the player
     * @return
     * 			true if a collision which need to be rule happens false otherwise
     */
    private boolean processPlayerDoorCollision(final Player player, final Door door) {
		if (door.getPlayerWhoCanOpen().get() == player) {
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
     * 			the player who collides with the sword
     * @param sword
     * 			the sword which collides with the player
     * @return 
     * 			true if a collision which need to be rule happens false otherwise.
     * @throws Exception 
     */
    private boolean processPlayerSwordCollision(final Player player, final Sword sword) throws Exception {
		if (sword.getState() == EntityState.EQUIPPED && sword.getPlayer().get() != player) {
			player.die(); 
			this.outerWorld.notifyCollision(CollisionResult.PLAYERKILLED);
			return true;
		} else if (sword.getState() == EntityState.STAYING_STILL && !player.getWeapon().isPresent()) {
			player.equipWeapon(sword);
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

package it.unibo.ndgg.model.collision;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.CollisionAdapter;
import org.dyn4j.dynamics.contact.ContactConstraint;

import it.unibo.ndgg.model.entity.Entity;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.physic.body.BodyProperties;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * Represents a collision listener for physical collision between bodies used by the dyn4j library.
 * This class rules those collision.
 */
public class CollisionRules extends CollisionAdapter {
	private boolean isCollisionRulesAlreadyCreated = false;
    private static String COLLISION_ALREADY_CREATED_ERR = "COLLISION RULES ALREADY CREATED ERR";
    private final WorldImpl outerWorld;
    private final WorldProperties worldProperties;

    CollisionRules(final WorldImpl outerWorld, final WorldProperties worldProperties) {
        super();
        this.checkIfFirstIstance(isCollisionRulesAlreadyCreated, COLLISION_ALREADY_CREATED_ERR);
        this.isCollisionRulesAlreadyCreated = true;
        this.outerWorld = outerWorld;
        this.worldProperties = worldProperties;
    }

    public boolean collision(final ContactConstraint contactConstraint) {
		final Body firstBody = contactConstraint.getBody1();
		final Body secondBody = contactConstraint.getBody2();

		final Triple< Body, BodyProperties, EntityType > firstTriple = new ImmutableTriple<>
					(firstBody, this.worldProperties.getPhysicalBodyFromBody(firstBody), 
					this.worldProperties.getEntityTypeFromBody(firstBody));

		final Triple<Body, BodyProperties, EntityType> secondTriple = new ImmutableTriple<>
					(secondBody, this.worldProperties.getPhysicalBodyFromBody(secondBody), 
					this.worldProperties.getEntityTypeFromBody(secondBody));
		/*
		final Vector2 point = contactConstraint.getContacts().get(0).getPoint(); punto di contatto tra le due enitità per ora non serve.
		*/
		if (firstTriple.getRight() == EntityType.PLAYER && secondTriple.getRight() == EntityType.DOOR ||
			firstTriple.getRight() == EntityType.DOOR && secondTriple.getRight() == EntityType.PLAYER) {//verificare come fare nel caso in cui un giocatore con una spada in mano faccia costantemente collisioni.
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

    private void checkIfFirstIstance(final boolean condition, final String err) {
        if (condition) {
            throw new IllegalStateException(err);
        }
    }
}

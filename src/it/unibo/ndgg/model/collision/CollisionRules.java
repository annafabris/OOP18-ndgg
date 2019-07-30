package it.unibo.ndgg.model.collision;

import org.dyn4j.dynamics.CollisionAdapter;

import it.unibo.ndgg.model.entity.Entity;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.world.World;

public class CollisionRules extends CollisionAdapter {
	
	private boolean isCollisionRulesAlreadyCreated = false;
    private static String COLLISION_ALREADY_CREATED_ERR = "COLLISION RULES ALREADY CREATED ERR";
    private final World outerWorld;
    
    
    CollisionRules(final World outerWorld) {
        super();
        this.checkIfFirstIstance(isCollisionRulesAlreadyCreated, COLLISION_ALREADY_CREATED_ERR);
        this.isCollisionRulesAlreadyCreated = true;
        this.outerWorld = outerWorld;
    }
    
    private boolean processPlayerSwordCollision(final Player player,final Sword sword) {
		if (sword.getState() == EntityState.EQUIPPED && sword.getPlayer().get() != player) {
			player.die(); 
			this.outerWorld.notifyCollision(CollisionResult.PLAYERKILLED);
			return true;
		}
		else if (sword.getState() == EntityState.STAYING_STILL && !player.getWeapon().isPresent()) {
			sword.equipWeapon(player);
			player.equipWeapon(sword);
			this.outerWorld.notifyCollision(CollisionResult.SWORDPICKEDUP);
			return true ;
		}
		else if (sword.getState() == EntityState.DROPPING) {
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

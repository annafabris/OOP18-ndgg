package it.unibo.ndgg.view.entitydraw;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.SwordGuard;

/**
 * Represents an association between a {@link PlayerFrames} and a {@link it.unibo.ndgg.model.entity.EntityState} 
 * and {@link it.unibo.ndgg.model.entity.EntityDirection}. 
 * Each playerFrames represents a state with a particular direction.
 */
public class PlayerImage extends EntityFrameInformationImpl {

    private static final Map<Pair<EntityState, EntityDirection>, PlayerFrames> STATE = new HashMap<>();
    private static final String PATH_PLAYER_1 = "images/player_one/";
    private static final String PATH_PLAYER_2 = "images/player_two/";
    private static final String WITH_SWORD = "with_weapon/";
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 564;
    private static final String EXTENSION = ".png";

    static {
        STATE.put(Pair.of(EntityState.MOVING, EntityDirection.LEFT), PlayerFrames.IMAGE_MOVE_LEFT);
        STATE.put(Pair.of(EntityState.MOVING, EntityDirection.RIGHT), PlayerFrames.IMAGE_MOVE_RIGHT);
        STATE.put(Pair.of(EntityState.JUMPING, EntityDirection.RIGHT), PlayerFrames.IMAGE_JUMP_RIGHT);
        STATE.put(Pair.of(EntityState.JUMPING, EntityDirection.LEFT), PlayerFrames.IMAGE_JUMP_LEFT);
        STATE.put(Pair.of(EntityState.STAYING_STILL, EntityDirection.LEFT), PlayerFrames.IMAGE_IDLE_LEFT);
        STATE.put(Pair.of(EntityState.STAYING_STILL, EntityDirection.RIGHT), PlayerFrames.IMAGE_IDLE_RIGHT);
        STATE.put(Pair.of(EntityState.DYING, EntityDirection.LEFT), PlayerFrames.IMAGE_DIE_LEFT);
        STATE.put(Pair.of(EntityState.DYING, EntityDirection.RIGHT), PlayerFrames.IMAGE_DIE_RIGHT);
    }

    /**
     * Builds a {@link EntityFrameInformationImpl} thats contains the information about a frame.
     */
    public PlayerImage() {
        super(FRAME_HEIGHT, FRAME_WIDTH);
    }

    /**
     * Returns the player frame associates an EntityState and a Entitydirection.
     * @param state
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @return
     *          the player frame associates an EntityState and a EntityDirection
     */
    public PlayerFrames getPlayerFrames(final EntityState state, final EntityDirection direction) {
        return STATE.get(Pair.of(state, direction));
    }

    /**
     * Returns the number of frames in a specific sprite sheet.
     * @param state 
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @return
     *          the number of frames in the sprite sheet
     */
    public int getNumberOfFrames(final EntityState state, final EntityDirection direction) {
        return STATE.get(Pair.of(state, direction)).getNumberOfFrame();
    }

    /**
     * Return the player one sheet path.
     * @param state
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @param hasAWeapon 
     *          true if the weapon is present, otherwise false
     * @param guard
     *          it is the guard of the sword
     * @return
     *          the sprite sheet path
     */
    public String getPlayer1Path(final EntityState state, final EntityDirection direction, final boolean hasAWeapon,
                                 final Optional<SwordGuard> guard) {
        final PlayerFrames frame = this.getAHighSwordGuardIfPresent(guard, direction, state);
        if (hasAWeapon) {
            return PATH_PLAYER_1 + WITH_SWORD + frame.getPlayerSpriteSheet() + EXTENSION;
        } else {
            return PATH_PLAYER_1 + frame.getPlayerSpriteSheet() + EXTENSION;
        }
    }

    /**
     * Return the player one sheet path.
     * @param state
     *          it represents the state of the player to represent
     * @param direction
     *          it represents the direction of the player to represent 
     * @param hasAWeapon 
     *          true if the weapon is present, otherwise false
     * @param guard
     *          it is the guard of the sword
     * @return
     *          the sprite sheet path
     */
    public String getPlayer2Path(final EntityState state, final EntityDirection direction, final boolean hasAWeapon,
                                 final Optional<SwordGuard> guard) {
        final PlayerFrames frame = this.getAHighSwordGuardIfPresent(guard, direction, state);
        if (hasAWeapon) {
            return PATH_PLAYER_2 + WITH_SWORD + frame.getPlayerSpriteSheet() + EXTENSION;
        } else {
            return PATH_PLAYER_2 + frame.getPlayerSpriteSheet() + EXTENSION;
        }
    }

    private PlayerFrames getAHighSwordGuardIfPresent(final Optional<SwordGuard> guard, final EntityDirection direction,
                                                     final EntityState state) {
        if (guard.isPresent()) {
            if (guard.get() == SwordGuard.HIGH && state == EntityState.STAYING_STILL) {
                if (direction == EntityDirection.RIGHT) {
                    return PlayerFrames.IMAGE_GUARD_RIGHT; 
                } else {
                    return PlayerFrames.IMAGE_GUARD_LEFT;
                }
            }
        }
        return STATE.get(Pair.of(state, direction));
    }

}

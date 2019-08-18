package it.unibo.ndgg.view.entitydraw.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.SwordGuard;
import it.unibo.ndgg.view.entitydraw.EntityFrameInformationImpl;
import javafx.scene.image.Image;

/**
 * Represents an association between a {@link PlayerFrames} and a {@link it.unibo.ndgg.model.entity.EntityState} 
 * and {@link it.unibo.ndgg.model.entity.EntityDirection}. 
 * Each playerFrames represents a state with a particular direction.
 */
public class PlayerImage extends EntityFrameInformationImpl implements DynamicImage {

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
        STATE.put(Pair.of(EntityState.JUMPING_UP, EntityDirection.RIGHT), PlayerFrames.IMAGE_JUMP_UP_RIGHT);
        STATE.put(Pair.of(EntityState.JUMPING_DOWN, EntityDirection.RIGHT), PlayerFrames.IMAGE_JUMP_DOWN_RIGHT);
        STATE.put(Pair.of(EntityState.JUMPING_UP, EntityDirection.LEFT), PlayerFrames.IMAGE_JUMP_UP_LEFT);
        STATE.put(Pair.of(EntityState.JUMPING_DOWN, EntityDirection.LEFT), PlayerFrames.IMAGE_JUMP_DOWN_LEFT);
        STATE.put(Pair.of(EntityState.STAYING_STILL, EntityDirection.LEFT), PlayerFrames.IMAGE_IDLE_LEFT);
        STATE.put(Pair.of(EntityState.STAYING_STILL, EntityDirection.RIGHT), PlayerFrames.IMAGE_IDLE_RIGHT);
        STATE.put(Pair.of(EntityState.DYING, EntityDirection.LEFT), PlayerFrames.IMAGE_DIE_LEFT);
        STATE.put(Pair.of(EntityState.DYING, EntityDirection.RIGHT), PlayerFrames.IMAGE_DIE_RIGHT);
    }

    private final boolean isThePlayerOne;

    /**
     * Builds a {@link EntityFrameInformationImpl} thats contains the information about a frame.
     * @param isThePlayerOne
     *          true if it's the first player, otherwise false
     */
    public PlayerImage(final boolean isThePlayerOne) {
        super(FRAME_HEIGHT, FRAME_WIDTH);
        this.isThePlayerOne = isThePlayerOne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfFrames(final EntityState state, final EntityDirection direction) {
        return STATE.get(Pair.of(state, direction)).getNumberOfFrame();
    }

    /**
     * Returns the image that represents a particular state and a direction of a player.
     * @param state
     *          it is the player's state
     * @param direction
     *          it is the player's direction
     * @param hasAWeapon
     *          true if the player has a weapon, otherwise false
     * @param guard
     *          it is a empty optional if the sword isn't present, otherwise represents the sword guard
     * @return
     *          the image that represents one of players with his direction and state
     */
    public Image getImage(final EntityState state, final EntityDirection direction, final boolean hasAWeapon,
                          final Optional<SwordGuard> guard) {
        if (this.isThePlayerOne) {
            return new Image(this.getPlayer1Path(state, direction, hasAWeapon, guard));
        } else {
            return new Image(this.getPlayer2Path(state, direction, hasAWeapon, guard));
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Pair<EntityState, EntityDirection>> getAllPossibleStates() {
        return STATE.keySet().stream().collect(Collectors.toList());
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
    private String getPlayer1Path(final EntityState state, final EntityDirection direction, final boolean hasAWeapon,
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
    private String getPlayer2Path(final EntityState state, final EntityDirection direction, final boolean hasAWeapon,
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

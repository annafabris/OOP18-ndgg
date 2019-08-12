package it.unibo.ndgg.view.entitydraw;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;

/**
 * Represents an implementation of PlayerImage, it includes all states and direction with
 * their correspondent sprite sheet and frame number.
 */
public class PlayerImageImpl extends EntityFrameInformationImpl implements PlayerImage {

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
    public PlayerImageImpl() {
        super(FRAME_HEIGHT, FRAME_WIDTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerFrames getPlayerFrames(final EntityState state, final EntityDirection direction) {
        return STATE.get(Pair.of(state, direction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfFrames(final EntityState state, final EntityDirection direction) {
        return STATE.get(Pair.of(state, direction)).getNumberOfFrame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayer1Path(final EntityState state, final EntityDirection direction, final boolean hasAWeapon) {
        if (hasAWeapon) {
            return PATH_PLAYER_1 + WITH_SWORD + this.getPlayerFrames(state, direction).getPlayerSpriteSheet() + EXTENSION;
        } else {
            return PATH_PLAYER_1 + this.getPlayerFrames(state, direction).getPlayerSpriteSheet() + EXTENSION;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayer2Path(final EntityState state, final EntityDirection direction, final boolean hasAWeapon) {
        if (hasAWeapon) {
            return PATH_PLAYER_2 + WITH_SWORD + this.getPlayerFrames(state, direction).getPlayerSpriteSheet() + EXTENSION;
        } else {
            return PATH_PLAYER_2 + this.getPlayerFrames(state, direction).getPlayerSpriteSheet() + EXTENSION;
        }
    }

}

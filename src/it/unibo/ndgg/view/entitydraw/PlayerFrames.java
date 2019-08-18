package it.unibo.ndgg.view.entitydraw;

/**
 * Represents the number of frame in the sprite sheet.
 */
public enum PlayerFrames {

    /**
     * This is the player sprite sheet that represents the moving left.
     */
    IMAGE_MOVE_LEFT("move_left", 5),

    /**
     * This is the player sprite sheet that represents the moving right.
     */
    IMAGE_MOVE_RIGHT("move_right", 5),

    /**
     * This is the player sprite sheet that represents the dying right.
     */
    IMAGE_DIE_RIGHT("dead_right", 6),

    /**
     * This is the player sprite sheet that represents the dying left.
     */
    IMAGE_DIE_LEFT("dead_left", 6),

    /**
     * This is the player sprite sheet that represents the state idle.
     */
    IMAGE_IDLE_RIGHT("idle_right", 1),

    /**
     * This is the player sprite sheet that represents the state idle.
     */
    IMAGE_IDLE_LEFT("idle_left", 1),

    /**
     * This is the player sprite sheet that represents the jump up right.
     */
    IMAGE_JUMP_UP_RIGHT("jump_up_right", 4),

    /**
     * This is the player sprite sheet that represents the jump down right.
     */
    IMAGE_JUMP_DOWN_RIGHT("jump_down_right", 4),

    /**
     * This is the player sprite sheet that represents the jump up left.
     */
    IMAGE_JUMP_UP_LEFT("jump_up_left", 4),

    /**
     * This is the player sprite sheet that represents the jump down left.
     */
    IMAGE_JUMP_DOWN_LEFT("jump_down_left", 4),

    /**
     * This is the change of guard for the player in direction left.
     */
    IMAGE_GUARD_LEFT("idle_left_high", 1),

    /**
     * This is the change of guard for the player in direction right.
     */
    IMAGE_GUARD_RIGHT("idle_right_high", 1);

    private final String playerSpriteSheet;
    private final int numberOfFrames;

    PlayerFrames(final String playerSpriteSheet, final int numberOfFrames) {
        this.playerSpriteSheet = playerSpriteSheet;
        this.numberOfFrames = numberOfFrames;
    }

    /**
     * Returns the image URL of the {@link Player}.
     * @return 
     *        the associated url of the image
     */
    public String getPlayerSpriteSheet() {
        return this.playerSpriteSheet;
    }

    /**
     * Returns the number of frame in the sprite sheet.
     * @return
     *       the number of frame in the image
     */
    public int getNumberOfFrame() {
        return this.numberOfFrames;
    }

}

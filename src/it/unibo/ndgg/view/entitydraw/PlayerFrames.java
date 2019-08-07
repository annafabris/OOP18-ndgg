package it.unibo.ndgg.view.entitydraw;

/**
 * Represents the number of frame in the sprite sheet.
 */
public enum PlayerFrames {

    /**
     * This is the player sprite sheet that represents the moving left.
     */
    IMAGE_MOVE_LEFT("run_left", 5),

    /**
     * This is the player sprite sheet that represents the moving right.
     */
    IMAGE_MOVE_RIGHT("run_right", 5),

    /**
     * This is the player sprite sheet that represents the dying right.
     */
    IMAGE_DIE_RIGHT("dead_right", 6),

    /**
     * This is the player sprite sheet that represents the dying left.
     */
    IMAGE_DIE_LEFT("dead_left.png", 6),

    /**
     * This is the player sprite sheet that represents the state idle.
     */
    IMAGE_IDLE_RIGHT("idle_right", 1),

    /**
     * This is the player sprite sheet that represents the state idle.
     */
    IMAGE_IDLE_LEFT("idle_left", 1),

    /**
     * This is the player sprite sheet that represents the jump right.
     */
    IMAGE_JUMP_RIGHT("jump_right", 6),

    /**
     * This is the player sprite sheet that represents the jump left.
     */
    IMAGE_JUMP_LEFT("jump_left", 6);

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

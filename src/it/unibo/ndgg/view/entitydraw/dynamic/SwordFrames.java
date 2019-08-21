package it.unibo.ndgg.view.entitydraw.dynamic;

/**
 * Represents all sword frames.
 */
public enum SwordFrames {

    /**
     * This the sword in right direction.
     */
    SWORD_RIGHT("sword_right", 1),

    /**
     * This is the sword in left direction.
     */
    SWORD_LEFT("sword_left", 1),

    /**
     * This is the right throw of the sword.
     */
    THROW_SWORD_RIGHT("throw_right", 3),

    /**
     * This is the left throw of the sword.
     */
    THROW_SWORD_LEFT("throw_left", 3);

    private final String swordSpriteSheet;
    private final int numberOfFrames;

    SwordFrames(final String swordSpriteSheet, final int numberOfFrames) {
        this.swordSpriteSheet = swordSpriteSheet;
        this.numberOfFrames = numberOfFrames;
    }

    /**
     * Returns the image URL of the {@link sword}.
     * @return 
     *        the associated URL of the image
     */
    public String getSwordSpriteSheet() {
        return this.swordSpriteSheet;
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

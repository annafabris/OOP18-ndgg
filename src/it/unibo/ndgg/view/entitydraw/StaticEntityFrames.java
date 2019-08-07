package it.unibo.ndgg.view.entitydraw;

/**
 * Represents all static entities's sprite.
 */
public enum StaticEntityFrames {

    /**
     * Represents the player one door allocated in the right of the scene.
     */
    DOOR_RIGHT("door_player_one"),

    /**
     * Represents the player two door allocated in the left of the scene.
     */
    DOOR_LEFT("door_player_two");

    private static final String PATH = "/images/";
    private static final String EXTENSION = ".png";
    private final String staticEntitySprite;

    StaticEntityFrames(final String staticEntitySprite) {
        this.staticEntitySprite = staticEntitySprite;
    }

    /**
     * Returns the frame url of the static entity.
     * @return
     *          the frame path of the static entity
     */
    public String getFrameUrl() {
       return PATH + staticEntitySprite + EXTENSION;
    }

}

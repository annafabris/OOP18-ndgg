package it.unibo.ndgg.view.entitydraw;

/**
 * Represents all static entities's sprite.
 */
public enum BackgroundFrames {

    /**
     * Represents the first possible background.
     */
    BACKGROUND_1("background_1"),

    /**
     * Represents the second possible background.
     */
    BACKGROUND_2("background_2"),

    /**
     * Represents the third possible background.
     */
    BACKGROUND_3("background_3");


    private static final String PATH = "images/";
    private static final String EXTENSION = ".png";
    private final String staticEntitySprite;

    BackgroundFrames(final String staticEntitySprite) {
        this.staticEntitySprite = staticEntitySprite;
    }

    /**
     * Returns the frame path of the static entity.
     * @return
     *          the frame path of the static entity
     */
    public String getFramePath() {
       return PATH + this.staticEntitySprite + EXTENSION;
    }
}

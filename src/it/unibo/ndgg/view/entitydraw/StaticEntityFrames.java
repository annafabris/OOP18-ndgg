package it.unibo.ndgg.view.entitydraw;

import it.unibo.ndgg.model.entity.EntityType;

/**
 * Represents all static entities's sprite.
 */
public enum StaticEntityFrames {

    /**
     * Represents the player one door allocated in the right of the scene.
     */
    DOOR_RIGHT(EntityType.DOOR, "door_player_one"),

    /**
     * Represents the player two door allocated in the left of the scene.
     */
    DOOR_LEFT(EntityType.DOOR, "door_player_two"),

    /**
     * Represents the platform where all entity are.
     */
    PLATFORM(EntityType.PLATFORM, "platform");

    private static final String PATH = "images/staticEntity/";
    private static final String EXTENSION = ".png";
    private final EntityType entityType;
    private final String staticEntitySprite;

    StaticEntityFrames(final EntityType entityType, final String staticEntitySprite) {
        this.staticEntitySprite = staticEntitySprite;
        this.entityType = entityType;
    }

    /**
     * Returns the frame url of the static entity.
     * @return
     *          the frame path of the static entity
     */
    public String getFrameUrl() {
       return PATH + this.staticEntitySprite + EXTENSION;
    }

    /**
     * Returns the {@link it.unibo.ndgg.model.entity.EntityType} of the entity.
     * @return
     *          {@link it.unibo.ndgg.model.entity.EntityType}
     */
    public EntityType getEntityType() {
        return this.entityType;
    }
}

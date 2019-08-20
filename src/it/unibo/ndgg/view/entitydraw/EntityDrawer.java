package it.unibo.ndgg.view.entitydraw;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.view.entitydraw.dynamic.PlayerAnimation;
import it.unibo.ndgg.view.entitydraw.dynamic.SwordAnimation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A class that draws the main Entities in the View.
 */
public class EntityDrawer {
    private static final double MODEL_WORLD_HEIGHT = 9.0;
    private static final double MODEL_WORLD_WIDTH = 16.0;
    private static final int PLATFORM = 0;
    private static final int DOOR_L = 1;
    private static final int DOOR_R = 2;
    private static final int BACKGROUND = 3;
    private final Integer worldWidth;
    private final Integer worldHeight;
    private final List<Image> images;

    /**
     * Builds a {@link Entity} drawer.
     * @param worldDimension 
     *                  the dimensions of the View
     * @param backgroundId 
     *                  the {@link BackgroundFrames} of the wanted background
     */
    public EntityDrawer(final MutablePair<Integer, Integer> worldDimension, final BackgroundFrames backgroundId) {
        this.worldWidth = worldDimension.getLeft();
        this.worldHeight = worldDimension.getRight();
        this.images = new ArrayList<>();
        this.images.add(0, new Image(StaticEntityFrames.PLATFORM.getFrameUrl()));
        this.images.add(1, new Image(StaticEntityFrames.DOOR_LEFT.getFrameUrl()));
        this.images.add(2, new Image(StaticEntityFrames.DOOR_RIGHT.getFrameUrl()));
        this.images.add(3, new Image(backgroundId.getFrameUrl(), this.worldWidth, this.worldHeight, false, false));
    }

    /**
     * Draws the {@link it.unibo.ndgg.model.entity.entitystatic.Platfom}.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     * @param platform
     *          it is the entity platform
     */
    public void drawMainPlatform(final GraphicsContext graphicsContext, final Platform platform) {
       final Double dimensionX = (platform.getDimension().getLeft()) * (this.worldWidth / MODEL_WORLD_WIDTH);
       final Double dimensionY = (platform.getDimension().getRight()) * (this.worldHeight / MODEL_WORLD_HEIGHT);
       graphicsContext.drawImage(this.images.get(PLATFORM), 
                                 (platform.getPosition().getLeft() + MODEL_WORLD_WIDTH / 2.0) * (this.worldWidth / MODEL_WORLD_WIDTH) - dimensionX / 2.0,
                                 (MODEL_WORLD_HEIGHT / 2.0 - platform.getPosition().getRight()) * (this.worldHeight / MODEL_WORLD_HEIGHT) - dimensionY / 2.0,
                                 dimensionX,
                                 dimensionY);
    }

    /**
     * Draws the two {@link it.unibo.ndgg.model.entity.entitystatic.Door}s.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     * @param door
     *          it represents the door to draw.
     * @param isTheFirstDoor
     *          true if this the door of the first player
     */
    public void drawDoors(final GraphicsContext graphicsContext, final Door door, final boolean isTheFirstDoor) {
        Image doorImage;
        if (isTheFirstDoor) {
            doorImage = this.images.get(DOOR_L);
        } else {
            doorImage = this.images.get(DOOR_R);
        }
        final Double dimensionX = (door.getDimension().getLeft()) * (this.worldWidth / MODEL_WORLD_WIDTH);
        final Double dimensionY = (door.getDimension().getRight() + 1.0) * (this.worldHeight / MODEL_WORLD_HEIGHT);
        graphicsContext.drawImage(doorImage, 
                                 (door.getPosition().getLeft() + MODEL_WORLD_WIDTH / 2.0) * (this.worldWidth / MODEL_WORLD_WIDTH) - dimensionX / 2.0,
                                 (MODEL_WORLD_HEIGHT / 2.0 - door.getPosition().getRight() + 0.21) * (this.worldHeight / MODEL_WORLD_HEIGHT) - dimensionY / 2.0,
                                  dimensionX,
                                  dimensionY);
    }

    /**
     * Draws the background.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     */
    public void drawBackground(final GraphicsContext graphicsContext) {
        graphicsContext.drawImage(this.images.get(BACKGROUND), 0, 0);
    }

   /**
    * Draws the players.
    * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
    * @param playerAnimation 
    *             it is its animation
    * @param player
    *             the player to draw
    */
    public void drawPlayer(final GraphicsContext graphicsContext, final PlayerAnimation playerAnimation, final Player player) {
        final Double dimensionX = (player.getDimension().getLeft()) * (this.worldWidth / MODEL_WORLD_WIDTH);
        final Double dimensionY = (player.getDimension().getRight()) * (this.worldHeight / MODEL_WORLD_HEIGHT);
        graphicsContext.drawImage(playerAnimation.updatePosition(), 
                                 (player.getPosition().getLeft() + MODEL_WORLD_WIDTH / 2.0) * (this.worldWidth / MODEL_WORLD_WIDTH) - dimensionX / 2.0,
                                 (MODEL_WORLD_HEIGHT / 2.0 - player.getPosition().getRight()) * (this.worldHeight / MODEL_WORLD_HEIGHT) - dimensionY / 2.0,
                                 this.worldWidth / MODEL_WORLD_WIDTH,
                                 this.worldHeight / MODEL_WORLD_HEIGHT);
    }

    /**
     * Draws the swords.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     * @param swordAnimation
     *             it is its animation
     * @param state 
     *             this is the current state of the sword
     * @param sword
     *             the sword to draw
     */
    public void drawSword(final GraphicsContext graphicsContext, final SwordAnimation swordAnimation,
                          final EntityState state, final Sword sword) {
        final Double dimensionX = (sword.getDimension().getLeft()) * this.worldWidth / MODEL_WORLD_WIDTH;
        final Double dimensionY = (sword.getDimension().getRight()) * this.worldHeight / MODEL_WORLD_HEIGHT;
        if (state != EntityState.EQUIPPED) {
            graphicsContext.drawImage(swordAnimation.updatePosition(), 
                                     (sword.getPosition().getLeft() + MODEL_WORLD_WIDTH / 2.0) * this.worldWidth / MODEL_WORLD_WIDTH - dimensionX / 2.0,
                                     (MODEL_WORLD_HEIGHT / 2.0 - sword.getPosition().getRight()) * this.worldHeight / MODEL_WORLD_HEIGHT - dimensionY / 2.0,
                                     dimensionX,
                                     dimensionY);
        }
    }
}


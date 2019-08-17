package it.unibo.ndgg.view.entitydraw;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * A class that draws the main Entities in the View.
 */
public class EntityDrawer {
    //TODO attenzione a valori dopo perchè assegnati dopo
    private static final double PLATFORM_HEIGHT_POSITION_PERCENTAGE = 0.75;
    private static final int TILES_COLUMN_NUMBER = 21;
    private static final double PLATFORM_TILE_HEIGHT_PERCENTAGE = 0.067;
    private static final double PLATFORM_TILE_WIDTH_PERCENTAGE = 0.055;
    private static final double DOOR_HEIGHT_PERCENTAGE = 0.286;
    private static final double DOOR_WIDTH_PERCENTAGE = 0.125;
    private static final double DOOR_LEFT_SHIFT_PERCENTAGE = 0.00653;
    private static final double DOOR_RIGHT_SHIFT_PERCENTAGE = 0.038;
    private static final double PLATFORM_SHIFT_PERCENTAGE = 0.053;
    private static final double PLAYER_HEIGHT_PERCENTAGE = 0.280;
    private static final double PLAYER_WIDTH_PERCENTAGE = 0.110;
    private final Integer worldWidth;
    private final Integer worldHeight;
    private final List<Image> images;

    /**
     * 
     * @param worldDimension the dimensions of the View
     * @param backgroundId the {@link BackgroundFrames} of the wanted background
     */
    public EntityDrawer(final MutablePair<Integer, Integer> worldDimension, final BackgroundFrames backgroundId) {
        this.worldWidth = worldDimension.getLeft();
        this.worldHeight = worldDimension.getRight();
        this.images = new ArrayList<>();
        this.images.add(0, new Image(StaticEntityFrames.PLATFORM_BORDER.getFrameUrl(), 
                PLATFORM_TILE_WIDTH_PERCENTAGE * this.worldWidth, 
                PLATFORM_TILE_HEIGHT_PERCENTAGE * this.worldHeight, false, false));
        this.images.add(1, new Image(StaticEntityFrames.PLATFORM_CENTER.getFrameUrl(),
                PLATFORM_TILE_WIDTH_PERCENTAGE * this.worldWidth, 
                PLATFORM_TILE_HEIGHT_PERCENTAGE * this.worldHeight, false, false));
        this.images.add(2, new Image(StaticEntityFrames.DOOR_LEFT.getFrameUrl(), DOOR_WIDTH_PERCENTAGE * this.worldWidth, 
                DOOR_HEIGHT_PERCENTAGE * this.worldHeight, false, false));
        this.images.add(3, new Image(StaticEntityFrames.DOOR_RIGHT.getFrameUrl(), DOOR_WIDTH_PERCENTAGE * this.worldWidth, 
                DOOR_HEIGHT_PERCENTAGE * this.worldHeight, false, false));
        this.images.add(4, new Image(backgroundId.getFrameUrl(), this.worldWidth, this.worldHeight, false, false));
    }

    /**
     * Draws the {@link it.unibo.ndgg.model.entity.entitystatic.Platfom}.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     */
    public void drawMainPlatform(final GraphicsContext graphicsContext) {
        for (int i = 0; i <= TILES_COLUMN_NUMBER; i++) {
            graphicsContext.drawImage(this.images.get(1), -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight + 3 * this.images.get(1).getHeight());
            graphicsContext.drawImage(this.images.get(1), -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight + this.images.get(1).getHeight());
            graphicsContext.drawImage(this.images.get(1), -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight + 2 * this.images.get(1).getHeight());
            graphicsContext.drawImage(this.images.get(0), -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), (PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight));
        }
    }

    /**
     * Draws the two {@link it.unibo.ndgg.model.entity.entitystatic.Door}s.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     */
    public void drawDoors(final GraphicsContext graphicsContext) {
        graphicsContext.drawImage(this.images.get(2), -DOOR_LEFT_SHIFT_PERCENTAGE * this.worldWidth, PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight
                - this.images.get(2).getHeight() + DOOR_RIGHT_SHIFT_PERCENTAGE * this.worldWidth);
        graphicsContext.drawImage(this.images.get(3), this.worldWidth - this.images.get(3).getWidth(), 
                PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight - this.images.get(3).getHeight() + DOOR_RIGHT_SHIFT_PERCENTAGE * this.worldWidth);
    }

    /**
     * Draws the background.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     */
    public void drawBackground(final GraphicsContext graphicsContext) {
        graphicsContext.drawImage(this.images.get(4), 0, 0);
    }

   /**
    * Draws the players.
    * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
    * @param positionX
    *             this is the variable position x of the player
    */
    public void drawPlayer(final GraphicsContext graphicsContext, final PlayerAnimation playerAnimation,
                           final double positionX) {
        graphicsContext.drawImage(playerAnimation.updatePosition(), 
                                  positionX,
                                  495,
                                  100, 
                                  110);
    }

}

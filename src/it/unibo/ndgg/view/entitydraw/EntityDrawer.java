package it.unibo.ndgg.view.entitydraw;

import org.apache.commons.lang3.tuple.MutablePair;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
    private int worldWidth;
    private int worldHeight;

    public EntityDrawer(final MutablePair<Integer, Integer> worldDimension) {
        this.worldWidth = worldDimension.getLeft();
        this.worldHeight = worldDimension.getRight();
    }

    /**
     * Draws the {@link it.unibo.ndgg.model.entity.entitystatic.Platfom}.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     */
    public void drawMainPlatform(final GraphicsContext graphicsContext) {
        Image platformBorder = new Image(StaticEntityFrames.PLATFORM_BORDER.getFrameUrl(), 
                PLATFORM_TILE_WIDTH_PERCENTAGE * this.worldWidth, 
                PLATFORM_TILE_HEIGHT_PERCENTAGE * this.worldHeight, false, false);
        Image platformCenter = new Image(StaticEntityFrames.PLATFORM_CENTER.getFrameUrl(), 
                PLATFORM_TILE_WIDTH_PERCENTAGE * this.worldWidth, 
                PLATFORM_TILE_HEIGHT_PERCENTAGE * this.worldHeight, false, false);
        for (int i = 0; i <= TILES_COLUMN_NUMBER; i++) {
            graphicsContext.drawImage(platformCenter, -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight + 3 * platformCenter.getHeight());
            graphicsContext.drawImage(platformCenter, -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight + platformCenter.getHeight());
            graphicsContext.drawImage(platformCenter, -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight + 2 * platformCenter.getHeight());
            graphicsContext.drawImage(platformBorder, -PLATFORM_SHIFT_PERCENTAGE * this.worldWidth + this.worldWidth * i 
                    / (TILES_COLUMN_NUMBER - 1), (PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight));
        }
    }

    /**
     * Draws the two {@link it.unibo.ndgg.model.entity.entitystatic.Door}s.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     */
    public void drawDoors(final GraphicsContext graphicsContext) {
        Image door1 = new Image(StaticEntityFrames.DOOR_LEFT.getFrameUrl(), DOOR_WIDTH_PERCENTAGE * this.worldWidth, 
                DOOR_HEIGHT_PERCENTAGE * this.worldHeight, false, false);
        Image door2 = new Image(StaticEntityFrames.DOOR_RIGHT.getFrameUrl(), DOOR_WIDTH_PERCENTAGE * this.worldWidth, 
                DOOR_HEIGHT_PERCENTAGE * this.worldHeight, false, false);
        graphicsContext.drawImage(door1, -DOOR_LEFT_SHIFT_PERCENTAGE * this.worldWidth, PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight
                - door1.getHeight() + DOOR_RIGHT_SHIFT_PERCENTAGE * this.worldWidth);
        graphicsContext.drawImage(door2, this.worldWidth - door2.getWidth(), 
                PLATFORM_HEIGHT_POSITION_PERCENTAGE * this.worldHeight - door2.getHeight() + DOOR_RIGHT_SHIFT_PERCENTAGE * this.worldWidth);
    }

    /**
     * Draws the background.
     * @param graphicsContext {@link javafx.scene.canvas.GraphicsContext}
     * @param backgroundId the {@link BackgroundFrames} of the wanted background
     */
    public void drawBackground(final GraphicsContext graphicsContext, final BackgroundFrames backgroundId) {
        Image background = new Image(backgroundId.getFrameUrl(), this.worldWidth, this.worldHeight, false, false);
        graphicsContext.drawImage(background, 0, 0);
    }

}

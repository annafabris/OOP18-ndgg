package it.unibo.ndgg.view.entitydraw;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.view.ImageEntityAssociations;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A class that draws ... in the View
 */
public class EntityDrawer {

    private static final int WORLD_WIDTH = 920;
    private static final int WORLD_HEIGHT = 540;
    private static final int PLATFORM_HEIGHT = WORLD_HEIGHT / 5;
    private final ImageEntityAssociations images = new ImageEntityAssociations();

    /**
     * Draws the {@link it.unibo.ndgg.model.entity.entitystatic.Platfom}.
     * @param graphicsContext
     */
    public void drawMainPlatform (GraphicsContext graphicsContext) {
        Image platformBorder = new Image(this.images.getImage(EntityType.PLATFORM , 0), WORLD_WIDTH / 20, WORLD_HEIGHT / 15, false, false);
        Image platformCenter = new Image(this.images.getImage(EntityType.PLATFORM , 1), WORLD_WIDTH / 20, WORLD_HEIGHT / 15, false, false);
        for (int i = 1; i < 22; i++){
            graphicsContext.drawImage(platformCenter, -50 + WORLD_WIDTH*i/20, PLATFORM_HEIGHT * 4 + platformCenter.getHeight());
            graphicsContext.drawImage(platformCenter, -50 + WORLD_WIDTH*i/20, PLATFORM_HEIGHT * 4 + 2*platformCenter.getHeight());
            graphicsContext.drawImage(platformBorder, -50 + WORLD_WIDTH*i/20, PLATFORM_HEIGHT * 4);
        }
    }

    /**
     * Draws the two {@link it.unibo.ndgg.model.entity.entitystatic.Door}s.
     * @param graphicsContext
     */
    public void drawDoors (GraphicsContext graphicsContext) {
        Image door1 = new Image(this.images.getImage(EntityType.DOOR, 0), WORLD_WIDTH / 8, WORLD_HEIGHT / 3.5, false, false);
        Image door2 = new Image(this.images.getImage(EntityType.DOOR, 1), WORLD_WIDTH / 8, WORLD_HEIGHT /3.5, false, false);
        graphicsContext.drawImage(door1, 0, PLATFORM_HEIGHT * 4 - door1.getHeight() + 35);
        graphicsContext.drawImage(door2, WORLD_WIDTH - door2.getWidth(), PLATFORM_HEIGHT * 4 - door2.getHeight() + 35);
    }

    /**
     * Draws the background.
     * @param graphicsContext
     */
    public void drawBackground (GraphicsContext graphicsContext, int backgroundId) {
        Image background = new Image(images.getBackground(backgroundId), WORLD_WIDTH, WORLD_HEIGHT, false, false);
        graphicsContext.drawImage(background, 0, 0);
    }

}

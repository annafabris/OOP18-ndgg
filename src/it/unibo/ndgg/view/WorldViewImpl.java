package it.unibo.ndgg.view;

import it.unibo.ndgg.model.entity.EntityType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private static final String BACKGROUND = "images/bg1.png"; //TODO da togliere
    private static final int WORLD_HEIGHT = 540;
    private static final int WORLD_WIDTH = 920;
    private static final int PLATFORM_HEIGHT = WORLD_HEIGHT / 5;
    private final Group root = new Group();
    private final Stage stage;
    private final ImageEntityAssociations images;


    public WorldViewImpl(Stage stage) {
        this.stage = stage;
        this.images = new ImageEntityAssociations();
    }

    /**
     * {@inheritDoc}.
     * @throws Exception 
     */
    @Override
    public void startGame() throws Exception {
        this.stage.setScene(loadScene());
        this.stage.show();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    private Scene loadScene() throws Exception {
        this.stage.setTitle("Nidhogg");
        Canvas canvas = new Canvas(WORLD_WIDTH, WORLD_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        this.drawBackground(graphicsContext, 0);
        this.drawMainPlatform(graphicsContext);
        this.drawDoors(graphicsContext);

        stage.sizeToScene();
        return new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);
    }
    
    private void drawMainPlatform(GraphicsContext graphicsContext) {
        Image platform = new Image(this.images.getImage(EntityType.PLATFORM), WORLD_WIDTH / 20, WORLD_HEIGHT / 5, false, false);
        for(int x = 1; x < 22; x++){
            graphicsContext.drawImage(platform, -50 + WORLD_WIDTH*x/20, PLATFORM_HEIGHT * 4);
        }
    }
    
    private void drawDoors(GraphicsContext graphicsContext) {
        Image door1 = new Image(this.images.getImage(EntityType.DOOR, 0), WORLD_WIDTH / 8, WORLD_HEIGHT / 3.5, false, false);
        Image door2 = new Image(this.images.getImage(EntityType.DOOR, 1), WORLD_WIDTH / 8, WORLD_HEIGHT /3.5, false, false);
        graphicsContext.drawImage(door1, 0, PLATFORM_HEIGHT * 4 - door1.getHeight() + 35);
        graphicsContext.drawImage(door2, WORLD_WIDTH - door2.getWidth(), PLATFORM_HEIGHT * 4 - door2.getHeight() + 35);
    }

    private void drawBackground(GraphicsContext graphicsContext, int backgroundId) {
        Image background = new Image(images.getBackground(backgroundId), WORLD_WIDTH, WORLD_HEIGHT, false, false);
        graphicsContext.drawImage(background, 0, 0);
    }
}

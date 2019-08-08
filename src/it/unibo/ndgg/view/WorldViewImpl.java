package it.unibo.ndgg.view;

import it.unibo.ndgg.model.entity.EntityType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private static final String BACKGROUND = "images/bg1.png";
    private static final int WORLD_HEIGHT = 540;
    private static final int WORLD_WIDTH = 920;
    private final Group root = new Group();
    private final Stage stage;

    public WorldViewImpl(Stage stage) {
        this.stage = stage;
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
        ImageEntityAssociations images = new ImageEntityAssociations();

        //ImageView background = new ImageView(new Image(BACKGROUND));
        //GridPane gp = new GridPane();
        //gp.add(background, 0, 0);

        Canvas canvas = new Canvas(WORLD_WIDTH, WORLD_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image door = new Image(images.getImage(EntityType.DOOR));
        Image background = new Image(images.getImage(EntityType.DOOR));
        gc.drawImage(door, 40, 80); //test
        gc.drawImage(background, 0, 0);

        //root.getChildren().addAll(gc);
        stage.sizeToScene();

        return new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);
        //TODO questi numeri andrebbero messi come variabili?
    }
}

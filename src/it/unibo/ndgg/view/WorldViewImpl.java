package it.unibo.ndgg.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The class implementation of {@link WorldView}.
 */
public class WorldViewImpl implements WorldView {

    private static final String BACKGROUND = "images/bg1.png";
    Group root = new Group();
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
        
        ImageView background = new ImageView(new Image(BACKGROUND));
        GridPane gp = new GridPane();
        gp.add(background, 0, 0);

        root.getChildren().addAll(gp);
        stage.sizeToScene();
        
        return new Scene(root, 960, 540);
        //TODO questi numeri andrebbero messi come variabili? guardare anche ViewImpl ANNA
    }
}

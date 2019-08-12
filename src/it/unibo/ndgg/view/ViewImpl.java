package it.unibo.ndgg.view;

import javafx.stage.Stage;

public class ViewImpl implements View {

    private final Stage stage;

    public ViewImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub

    }

    public WorldView showGame() throws Exception {
        return new WorldViewImpl(stage);
    }    

}

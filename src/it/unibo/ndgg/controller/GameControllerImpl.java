package it.unibo.ndgg.controller;

import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.WorldView;
import it.unibo.ndgg.view.WorldViewImpl;

public class GameControllerImpl implements GameController, ViewObserver {

    private final MainController controller;
    private final WorldView view; //interfaccia
    private World gameWorld;

    public GameControllerImpl(WorldViewImpl view ,final MainController controller) throws Exception {
        this.controller = controller;
        this.view = view;
        this.gameWorld = new WorldImpl();
        game();
    }

    public void game() throws Exception {
        view.startGame();     
        this.gameWorld.start();
    }

    public void exit() {
        controller.quit();
    }

}

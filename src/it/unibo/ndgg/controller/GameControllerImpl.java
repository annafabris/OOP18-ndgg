package it.unibo.ndgg.controller;


import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.WorldView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameControllerImpl implements GameController {

    private final MainController controller;
    private final WorldView view; //interfaccia
    private World gameWorld;

    public GameControllerImpl(WorldView view ,final MainController controller) throws Exception {
        this.controller = controller;
        this.view = view;
        this.gameWorld = new WorldImpl();
        game();
    }

    public void game() throws Exception {
        this.gameWorld.start();
        view.startGame(this);
        this.updateModelAndView();
    }

    public void updateModelAndView() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.042),            // 24 FPS
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    GameState gameState = gameWorld.getCurrentGameState();
                    if (gameState == GameState.PLAYERL_WON) {
                        view.playerWon(0);
                        exit();
                    } else if (gameState == GameState.PLAYERL_WON) {
                        view.playerWon(0);
                        exit();
                    } else {
                        //TODO muovi giocatore e/o spada
                        gameWorld.update();
                        view.update();
                    }
                }
            });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }
    
    public void exit() {
        controller.quit();
    }
    
    public Player getPlayer(int playerID) {
        return this.gameWorld.getPlayer(playerID);
    }
    
    public Sword getSword(int swordId) {
        return this.gameWorld.getSword(swordId);
    }

}

package it.unibo.ndgg.controller;


import org.apache.commons.lang3.tuple.MutablePair;

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

/**
 * {@inheritDoc}.
 */
public class GameControllerImpl implements GameController {

    private static final double FPS = 24.0;
    private final MainController controller;
    private final WorldView view; //interfaccia
    private World gameWorld;

    public GameControllerImpl(final WorldView view ,final MainController controller) throws Exception {
        this.controller = controller;
        this.view = view;
        this.gameWorld = new WorldImpl(new MutablePair<>(1300.0, 600.0));
        game();
    }

    /**
     * {@inheritDoc}
     */
    public void game() throws Exception {
        this.gameWorld.start();
        view.startGame(this);
        this.updateModelAndView();
    }

    /**
     * {@inheritDoc}
     */
    public void updateModelAndView() {
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            Duration.seconds(1 / FPS),
            new EventHandler<ActionEvent>() {
                public void handle(final ActionEvent ae) {
                    GameState gameState = gameWorld.getCurrentGameState();
                    if (gameState == GameState.PLAYERL_WON) {
                        view.playerWon(0);
                        gameLoop.stop();
                        exit();
                    } else if (gameState == GameState.PLAYERL_WON) {
                        view.playerWon(0);
                        gameLoop.stop();
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

    /**
     * {@inheritDoc}
     */
    public void exit() {
        controller.quit();
    }

    /**
     * {@inheritDoc}
     */
    public Player getPlayer(final int playerID) {
        return this.gameWorld.getPlayer(playerID);
    }

    /**
     * {@inheritDoc}
     */
    public Sword getSword(final int swordId) {
        return this.gameWorld.getSword(swordId);
    }

}

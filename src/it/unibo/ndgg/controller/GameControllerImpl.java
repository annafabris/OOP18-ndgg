package it.unibo.ndgg.controller;


import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.world.World;
import it.unibo.ndgg.model.world.WorldImpl;
import it.unibo.ndgg.view.WorldView;

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
    }

    public void updateModelAndView() {
        GameState gameState = this.gameWorld.getCurrentGameState();
        if (gameState == GameState.PLAYERL_WON) {
            this.view.PlayerWon(0);
            this.exit();
        } else if(gameState == GameState.PLAYERL_WON) {
            this.view.PlayerWon(0);
            this.exit();
        } else {
            //TODO muovi giocatore e/o spada
            this.gameWorld.update();
            this.view.update();
        }

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

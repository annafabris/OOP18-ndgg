package it.unibo.ndgg.controller.commands;

import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
import it.unibo.ndgg.model.world.World;
/**
 * 
 */
public class MoveRight implements Command {

    private final World actor;

    /**
     * 
     * @param actor aa
     */
    public MoveRight(final World actor) {
        this.actor = actor;
    }

    @Override
    public void execute(final PlayerID player) {
        actor.movePlayerRight(player);
    }

}

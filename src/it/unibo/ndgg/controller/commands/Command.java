package it.unibo.ndgg.controller.commands;

import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;

/**
 * 
 */
public interface Command {

    /**
     * @param player a
     */
    void execute(PlayerID player);

}

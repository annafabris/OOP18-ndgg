package it.unibo.ndgg.model.entity.entitydynamic;
/**
 * 
 */
public enum PlayerID {

    /**
     * 
     */
    FIRST_PLAYER(0),

    /**
     * 
     */
    SECOND_PLAYER(1);
    
    private final int id;
    
    PlayerID(final int id){
        this.id = id;
    }
    
    public int getID() {
        return id;
    }

}

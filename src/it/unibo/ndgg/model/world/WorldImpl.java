package it.unibo.ndgg.model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ndgg.model.collision.CollisionResult;

/**
 * {@inheritDoc}.
 */
public class WorldImpl implements World {

    private static final int NUMBER_OF_ROOMS = 14;
    private List<Room> rooms = new ArrayList<Room>();  
    private int currentRoom;

    public WorldImpl(int currentRoom) {
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList())); //TODO controllare
        this.currentRoom = NUMBER_OF_ROOMS / 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.rooms.get(this.currentRoom).update();
    }

    /**
     * Returns a number referring to the {@link Room} in which the game is taking place.
     * @return the number referring to the current {@link Room}
     */
    public int getCurrentRoom() {       //TODO serve?
        return this.currentRoom;
    }

    public void notifyCollision(final CollisionResult collisionType) {
        switch (collisionType) {
            case PLAYERKILLED:
                break;
            case DOORTOUCHED:
                this.changeRoom();
                break;
            case SWORDPICKEDUP:
                break;
            case PLAYERDISARMED:
                break;
            case SWORDONTHEGROUND:
                break;
            default:
                break;
        }
    }
 
    /**
     * A methods that gets called when {@link CollisionResult.DOORTOUCHED} happens and the currentRoom needs to change
     */
    private void changeRoom() {
        if (true) {//TODO condizione if
            this.currentRoom--;
        } else {
            this.currentRoom++;
        }
    }

}

package it.unibo.ndgg.model.world;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@inheritDoc}.
 */
public class WorldImpl implements World {

    private static final int NUMBER_OF_ROOMS = 14;
    private ArrayList<Room> rooms = new ArrayList<Room>();  //TODO solo list? ANNA
    private int currentRoom;
    
    public WorldImpl(int currentRoom) {
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList()));
        this.currentRoom = NUMBER_OF_ROOMS / 2;
    }

    public void update() {
        
    }
    
    public int getCurrentRoom() {
        return this.currentRoom;
    }
}

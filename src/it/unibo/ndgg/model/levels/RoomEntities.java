package it.unibo.ndgg.model.levels;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import it.unibo.ndgg.model.entity.EntityType;
/**
 * this class will be used if in the future developers want to add a different kind of rooms.
 * this class contains method to read from a binary file which represent a room the list of entities that will be part of the room.
 */
public class RoomEntities {
    private static final String FILE_PATH = "res/rooms/";
    private static final String FILE_EXT = ".bin";
    private final String roomFileName;
    private static final List<EntityType> COMPONENTS_OF_THE_WORLD = new ArrayList<EntityType>();
    static {
        COMPONENTS_OF_THE_WORLD.add(EntityType.DOOR);
        COMPONENTS_OF_THE_WORLD.add(EntityType.DOOR);
        COMPONENTS_OF_THE_WORLD.add(EntityType.SWORD);
        COMPONENTS_OF_THE_WORLD.add(EntityType.SWORD);
        COMPONENTS_OF_THE_WORLD.add(EntityType.PLAYER);
        COMPONENTS_OF_THE_WORLD.add(EntityType.PLAYER);
        COMPONENTS_OF_THE_WORLD.add(EntityType.PLATFORM);
    }
    /**
     * class constructor who need the name of the file that will be read.
     * @param name the name of the room file.
     */
    public RoomEntities(final String name) {
        this.roomFileName = name;
    }
    /**
     * this method will be used to read the list of entityType that are present in the selected room file.
     * @throws IOException Input/Output exception.
     * @throws ClassNotFoundException ClassNotFoundException.
     */
    public void readRoom() throws IOException, ClassNotFoundException {
        try {
            final InputStream buffStream = new BufferedInputStream(new FileInputStream(ClassLoader.getSystemResource(FILE_PATH + this.roomFileName + FILE_EXT).toExternalForm()));
            final ObjectInputStream objReader = new ObjectInputStream(buffStream); 
            objReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

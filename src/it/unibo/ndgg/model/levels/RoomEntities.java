package it.unibo.ndgg.model.levels;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import it.unibo.ndgg.model.entity.EntityType;

public class RoomEntities {
    final String filePath = "res/rooms/rooms.bin";
    
    
    
    
    
    
    
    
    
    
    
    
    public RoomEntities(){
        
    }
    
    
    private static final List<EntityType> ComponentsOfTheWorld = new ArrayList<EntityType>();
    static {
        ComponentsOfTheWorld.add(EntityType.DOOR);
        ComponentsOfTheWorld.add(EntityType.DOOR);
        ComponentsOfTheWorld.add(EntityType.SWORD);
        ComponentsOfTheWorld.add(EntityType.SWORD);
        ComponentsOfTheWorld.add(EntityType.PLAYER);
        ComponentsOfTheWorld.add(EntityType.PLAYER);
        ComponentsOfTheWorld.add(EntityType.PLATFORM);
    }
    public void ciao() throws IOException {
/*        final File file = new File(this.filePath);
        if(file.canWrite()) {
            System.out.println("possoscrivere");
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
        }
        if(!file.exists()){
            System.out.println("aiut");
            if(file.createNewFile()) {
                System.out.println("ma allora");
            }
            }
        //file.
        try(OutputStream bufferedStream =
                new BufferedOutputStream(new FileOutputStream(this.filePath));
                ObjectOutputStream objStream = new ObjectOutputStream(bufferedStream);){
            objStream.writeObject(RoomEntities.ComponentsOfTheWorld);
        }
       catch(IOException ex) {
           System.out.println("an I/O error occurred");
           ex.printStackTrace();
       }
        System.out.println("dioooo");*/
        
    }
    public void leggi() throws IOException, ClassNotFoundException {
        try {
            InputStream buffStream = new BufferedInputStream(new FileInputStream(this.filePath));
            int ciao = buffStream.available();
            System.out.println(String.valueOf(ciao));
            ObjectInputStream objReader = new ObjectInputStream(buffStream);{
                if(objReader.readObject().equals(RoomEntities.ComponentsOfTheWorld)) {
                    System.out.println("alleluia");
                }
                objReader.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}

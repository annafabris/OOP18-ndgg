package it.unibo.ndgg.model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.model.collision.CollisionResult;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityFactory;
import it.unibo.ndgg.model.entity.EntityFactoryImpl;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;

/**
 * {@inheritDoc}.
 */
public class WorldImpl implements World {

    private static final double WORLD_WIDTH = 960; //TODO da prendere in altro modo
    private static final double WORLD_HEIGHT = 450;
    private static final Double SWORD_HEIGHT = 0.5;
    private static final Double SWORD_WIDTH = 0.5;
    private static final int NUMBER_OF_ROOMS = 3;
    private List<Room> rooms = new ArrayList<Room>();
    private int currentRoom;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private BodyAssociations bodyAssociations;
    private Map<EntityType, List<AbstractEntity>> entities;

    public WorldImpl() {
        this.entities = new HashMap<>();
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList())); //TODO controllare
        this.currentRoom = NUMBER_OF_ROOMS / 2;
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesFactory = new BodyPropertiesFactory();
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this, 1.0, 1.0, this.bodyAssociations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this, WORLD_WIDTH, WORLD_HEIGHT, bodyAssociations);
        createEntities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.rooms.get(this.currentRoom).update();
        this.bodyPropertiesWorld.update();
        //TODO se volete posso rimuovere le spade qui
    }

    /**
     * Returns a number referring to the {@link Room} in which the game is taking place.
     * @return the number referring to the current {@link Room}
     */
    public int getCurrentRoom() {       //TODO serve?
        return this.currentRoom;
    }

    /**
     * {@inheritDoc}.
     */
    public void notifyCollision(final CollisionResult collisionResult) {
        switch (collisionResult) {
            case PLAYERKILLED:
                this.changeRoom();
                break;
            case DOORTOUCHED:
                this.changeRoom();
                break;
            case SWORDPICKEDUP:
                this.addSwordToPlayer();
                break;
            case PLAYERDISARMED:
                this.removeSwordToPlayer();
                break;
            case SWORDONTHEGROUND:
                break;
            default:
                break;
        }
    }
 
    private void removeSwordToPlayer() {

    }

    private void addSwordToPlayer() {
        // TODO Auto-generated method stub

    }
    
    /**
     * A methods that gets called when {@link CollisionResult.DOORTOUCHED} or {@link CollisionResult.PLAYERKILLED} 
     * happens and the currentRoom needs to change.
     */
    private void changeRoom() {
        Door doorL = (Door) this.entities.get(EntityType.DOOR).get(0);
        Door doorR = (Door) this.entities.get(EntityType.DOOR).get(1);
        Player playerL = (Player) this.entities.get(EntityType.PLAYER).get(0);
        Player playerR = (Player) this.entities.get(EntityType.PLAYER).get(1);
        
        if (doorL.getDoorStatus() || (!playerL.isAlive() && playerR.isAlive())) {       
            if(this.currentRoom == 0) {
                //TODO game ended PlayerR won
            } else {
                this.currentRoom--;
                //TODO segnalarlo a View
            }
        } else if (doorR.getDoorStatus() || (playerL.isAlive() && !playerR.isAlive())) {
            if (this.currentRoom == WorldImpl.NUMBER_OF_ROOMS - 1) {
                //TODO game ended PlayerL won
            } else {
                this.currentRoom++;
            }
        }
    }

    private void createEntities() {
        EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        Player playerL = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(-1.0, 0.0), EntityDirection.RIGHT);
        Player playerR = entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(1.0, 0.0), EntityDirection.LEFT);
        entities.put(EntityType.PLAYER, Stream.of(playerL, playerR).collect(Collectors.toList()));
        entities.put(EntityType.SWORD, Stream.of(
                (Sword) entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, new MutablePair<>(1.0, 5.0), playerL, EntityDirection.RIGHT), 
                (Sword) entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, new MutablePair<>(1.0, -5.0), playerR, EntityDirection.LEFT))
                .collect(Collectors.toList()));
        this.bodyAssociations.setEntities(entities);
        this.rooms.get(this.currentRoom).setEntities(entities);
    }
}

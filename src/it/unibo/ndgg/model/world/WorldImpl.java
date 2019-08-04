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
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;

/**
 * {@inheritDoc}.
 */
public class WorldImpl implements World {

    private static final Double SWORD_HEIGHT = 0.5;
    private static final Double SWORD_WIDTH = 0.5;
    private static final int NUMBER_OF_ROOMS = 3;
    private List<Room> rooms = new ArrayList<Room>();
    private int currentRoom;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private BodyAssociations bodyAssociations;

    public WorldImpl() {
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList())); //TODO controllare
        this.currentRoom = NUMBER_OF_ROOMS / 2;
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesFactory = new BodyPropertiesFactory();
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createPhysicalWorld(1.0, 1.0, this.bodyAssociations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        createEntities();
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

    /**
     * {@inheritDoc}.
     */
    public void notifyCollision(final CollisionResult collisionResult) {
        switch (collisionResult) {
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
     * A methods that gets called when {@link CollisionResult.DOORTOUCHED} happens and the currentRoom needs to change.
     */
    private void changeRoom() {
        /*if (this.currentRoom == 0 && .getDoorStatus()) {
            //TODO game ended
            return;
        } else if (this.currentRoom == WorldImpl.NUMBER_OF_ROOMS - 1 
                && this.rooms.get(this.currentRoom).getEntities().get(EntityType.SWORD).get(0).getDoorStatus()){
            //TODO game ended
            return;
        } else if ( .getDoorStatus()) {
            this.currentRoom--;
        } else {
            this.currentRoom++;
        }*/
    }

    private void createEntities() {
        Map<EntityType, List<AbstractEntity>> entities = new HashMap<>();
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createPhysicalWorld(2.0, 2.0, bodyAssociations);
        EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        Player playerR = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(1.0, 0.0), EntityDirection.LEFT);
        Player playerL = entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(-1.0, 0.0), EntityDirection.RIGHT);
        entities.put(EntityType.PLAYER, Stream.of(playerR, playerL).collect(Collectors.toList()));
        entities.put(EntityType.SWORD, Stream.of(
                (Sword) entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, new MutablePair<>(1.0, 5.0), playerR, EntityDirection.LEFT), 
                (Sword) entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, new MutablePair<>(1.0, -5.0), playerL, EntityDirection.RIGHT))
                .collect(Collectors.toList()));
        this.bodyAssociations.setEntities(entities);
        this.rooms.get(this.currentRoom).setEntities(entities);
    }
}

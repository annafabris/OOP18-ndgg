package it.unibo.ndgg.model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.MutablePair;

import it.unibo.ndgg.model.collision.CollisionResult;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.Entity;
import it.unibo.ndgg.model.entity.EntityFactoryImpl;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.physic.BodyPropertiesWorldImpl;

/**
 * {@inheritDoc}.
 */
public class WorldImpl implements World {

    private static final int NUMBER_OF_ROOMS = 14;
    private List<Room> rooms = new ArrayList<Room>();
    private int currentRoom;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private Map<EntityType, List<AbstractEntity>> entities;
    private BodyAssociations bodyAssociation;

    public WorldImpl() {
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList())); //TODO controllare
        this.currentRoom = NUMBER_OF_ROOMS / 2;
        this.bodyAssociation = new BodyAssociations();
        this.bodyPropertiesFactory = new BodyPropertiesFactory();
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createPhysicalWorld(1.0, 1.0, this.bodyAssociation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        BodyPropertiesFactory bodyPropertiesFactory = new BodyPropertiesFactory();
        EntityFactoryImpl entityFactory = new EntityFactoryImpl();
        Player playerR = (Player) entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(1.0, 0.0));
        Player playerL = (Player) entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(-1.0, 0.0));
        this.entities.put(EntityType.PLAYER, Stream.of(playerR, playerL).collect(Collectors.toList()));
        this.entities.put(EntityType.SWORD, Stream.of(
                (Sword) entityFactory.createSword(2.0, 40.0, new MutablePair<>(1.0, 5.0), playerR), 
                (Sword) entityFactory.createSword(2.0, 40.0, new MutablePair<>(1.0, 5.0), playerL))
                .collect(Collectors.toList()));
        this.bodyAssociation.setEntities(entities);
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
     * A methods that gets called when {@link CollisionResult.DOORTOUCHED} happens and the currentRoom needs to change.
     */
    private void changeRoom() {
        if (true) {//TODO condizione if
            this.currentRoom--;
        } else {
            this.currentRoom++;
        }
    }

}

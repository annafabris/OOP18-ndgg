package it.unibo.ndgg.model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;

import it.unibo.ndgg.model.GameState;
import it.unibo.ndgg.model.collision.CollisionResult;
import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityFactory;
import it.unibo.ndgg.model.entity.EntityFactoryImpl;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;

/**
 * {@inheritDoc}.
 */
public class WorldImpl implements World {

    private static final double PLAYER_HEIGHT_PERCENTAGE = 0.17;
    private static final double PLAYER_WIDTH_PERCENTAGE = 0.095;
    private static final double SWORD_HEIGHT_PERCENTAGE = 0.018;
    private static final double SWORD_WIDTH_PERCENTAGE = 0.04;
    private static final int NUMBER_OF_ROOMS = 3;
    private List<Room> rooms = new ArrayList<Room>();
    private int currentRoom;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private BodyAssociations bodyAssociations;
    private Map<EntityType, List<AbstractEntity>> entities;
    private GameState currentGameState;
    private Pair<Double, Double> worldDimension;

    public WorldImpl(Pair<Double, Double> worldDimension) {
        this.worldDimension = worldDimension;
        this.currentGameState = GameState.IS_GOING;
        this.entities = new HashMap<>();
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList())); //TODO controllare
        this.currentRoom = NUMBER_OF_ROOMS / 2;
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesFactory = new BodyPropertiesFactory();
        //this.bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this, 1000, 1000, this.bodyAssociations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this, this.worldDimension.getLeft(), 
                this.worldDimension.getRight(), bodyAssociations);
        createEntities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.rooms.get(this.currentRoom).update();
        this.bodyPropertiesWorld.update();
        Player player1 = getPlayer(0);
        System.out.println("df " + player1.getPosition() + "");
        if(this.bodyPropertiesWorld.getWorld().getBounds().isOutside(this.entities.get(EntityType.PLATFORM).get(0).getBody().getPhysicalBody())){
            System.exit(1);
        }

        Body body1 = player1.getBody().getPhysicalBody();
        if (body1.isInContact(this.entities.get(EntityType.PLATFORM).get(0).getBody().getPhysicalBody())) {
            System.out.println("trovato");
            //System.exit(1);
        }
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
        System.out.println("1");
        switch (collisionResult) {
            case PLAYERKILLED:
                this.changeRoom();
                System.out.println("1");
                break;
            case DOORTOUCHED:
                this.changeRoom();
                System.out.println("2");
                break;
            case SWORDPICKEDUP:
                System.out.println("3");
                break;
            case PLAYERDISARMED:
                System.out.println("4");
                break;
            case SWORDONTHEGROUND:
                System.out.println("5");
                break;
            default:
                break;
        }
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getCurrentGameState() {
        return this.currentGameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(final int playerId) {
        return (Player) this.entities.get(EntityType.PLAYER).get(playerId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sword getSword(final int swordId) {
        return (Sword) this.entities.get(EntityType.SWORD).get(swordId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform getPlatform() {
        return (Platform) this.entities.get(EntityType.PLATFORM).get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final EntityMovement movement, final int playerId) {
        Player player1 = getPlayer(playerId);
        if (movement != EntityMovement.STAY_STILL_LEFT && movement != EntityMovement.STAY_STILL_RIGHT) {
            player1.move(movement);
        }
    
        if (playerId == 1) {
            System.out.println(player1.getPosition() + " dddsfds");
        }
        //System.out.println("Active: " + body1.isActive());
        //System.out.println("\nAsleep:  " + body1.isAsleep());
        //System.out.println("Outside: "+this.bodyPropertiesWorld.getWorld().getBounds().isOutside(body1) + "\n");
        //System.out.println(this.worldDimension + "de");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveSword(final EntityMovement movement, final int swordId) {
        Player player = getPlayer(swordId);
        player.dropWeapon(movement);
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
            if (this.currentRoom == 0) {
                this.currentGameState = GameState.PLAYERR_WON;
            } else {
                this.currentRoom--;
                //TODO segnalarlo a View
            }
        } else if (doorR.getDoorStatus() || (playerL.isAlive() && !playerR.isAlive())) {
            if (this.currentRoom == WorldImpl.NUMBER_OF_ROOMS - 1) {
                this.currentGameState = GameState.PLAYERL_WON;
            } else {
                this.currentRoom++;
            }
        }
    }

    /**
     * Creates all the entities.
     */
    private void createEntities() {
        EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        Player playerL = entityFactory.createPlayer(1.0, 
                1.0, new MutablePair<Double, Double>
            (5.0, 1.0), EntityDirection.RIGHT);
        Player playerR = entityFactory.createPlayer(1.0, 1.0, new MutablePair<>
            (1.0, 1.0), EntityDirection.LEFT);
        entities.put(EntityType.PLAYER, Stream.of(playerL, playerR).collect(Collectors.toList()));
        entities.put(EntityType.SWORD, Stream.of(
                (Sword) entityFactory.createSword(this.worldDimension.getLeft() * SWORD_HEIGHT_PERCENTAGE, 
                        this.worldDimension.getRight() * PLAYER_WIDTH_PERCENTAGE, new MutablePair<>(100.0, 50.0), playerL, 
                        EntityDirection.RIGHT), 
                (Sword) entityFactory.createSword(this.worldDimension.getLeft() * SWORD_HEIGHT_PERCENTAGE, 
                        this.worldDimension.getRight() * SWORD_WIDTH_PERCENTAGE, new MutablePair<>(100.0, 50.0), playerR, EntityDirection.LEFT))
                .collect(Collectors.toList()));
       entities.put(EntityType.PLATFORM, Stream.of(entityFactory.createPlatform(16.0, 1.8, new MutablePair<Double, Double>(
                        0.0, -3.6))).collect(Collectors.toList()));
       this.bodyAssociations.setEntities(entities);
        this.rooms.get(this.currentRoom).setEntities(entities);
    }

}

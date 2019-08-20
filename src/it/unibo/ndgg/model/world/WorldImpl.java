package it.unibo.ndgg.model.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

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
import it.unibo.ndgg.model.entity.entitydynamic.PlayerID;
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

    private static final double PLAYER_HEIGHT = 0.8;
    private static final double PLAYER_WIDTH = 0.42;
    private static final double SWORD_HEIGHT = 0.05;
    private static final double SWORD_WIDTH = 0.3;
    private static final double PLATFORM_HEIGHT = 1.8;
    private static final double PLATFORM_WIDTH = 16;
    private static final double DOOR_HEIGHT = 1.6;
    private static final double DOOR_WIDTH = 1.8;
    private static final double PLAYER_X_POSITIOON = 5.0;
    private static final double PLAYER_Y_POSITIOON = -2.3;
    private static final double SWORD_X_POSITIOON = 5.0;
    private static final double SWORD_Y_POSITIOON = -2.3;
    private static final double PLATFORM_X_POSITIOON = 0.0;
    private static final double PLATFORM_Y_POSITIOON = -3.6;
    private static final double DOOR_X_POSITIOON = 7.0;
    private static final double DOOR_Y_POSITIOON = -1.65;
    private static final int NUMBER_OF_ROOMS = 3;
    private List<Room> rooms = new ArrayList<Room>();
    private int currentRoom;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private BodyAssociations bodyAssociations;
    private Map<EntityType, List<AbstractEntity>> entities;
    private GameState currentGameState;
    private Pair<Double, Double> worldDimension;

    /**
     * Creates the World that manages all the entities and rooms. 
     * @param worldDimension the dimensions of the current View
     */
    public WorldImpl(final Pair<Double, Double> worldDimension) {
        this.worldDimension = worldDimension;
        this.currentGameState = GameState.IS_GOING;
        this.entities = new HashMap<>();
        rooms.addAll(Stream.generate(() -> new RoomImpl()).limit(NUMBER_OF_ROOMS).collect(Collectors.toList())); //TODO controllare
        this.currentRoom = NUMBER_OF_ROOMS / 2;
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesFactory = new BodyPropertiesFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        //Da controllare 16 / 9
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this, 16, 9, bodyAssociations);
        createEntities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.rooms.get(this.currentRoom).update();
        this.bodyPropertiesWorld.update();
        /*Player player1 = getPlayer(0);
        System.out.println("df " + player1.getPosition() + "");
       if(this.bodyPropertiesWorld.getWorld().getBounds().isOutside(this.entities.get(EntityType.PLATFORM).get(0).getBody().getPhysicalBody())){
            System.exit(1);
        }

        Body body1 = player1.getBody().getPhysicalBody();
        if (body1.isInContact(this.entities.get(EntityType.PLATFORM).get(0).getBody().getPhysicalBody())) {
            System.out.println("trovato");
            //System.exit(1);
        }*/
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
                System.out.println("1 collision");
                break;
            case DOORTOUCHED:
                this.changeRoom();
                System.out.println("2 collision");
                break;
            case SWORDPICKEDUP:
                System.out.println("3 collision");
                break;
            case PLAYERDISARMED:
                System.out.println("4 collision");
                break;
            case SWORDONTHEGROUND:
                System.out.println("5 collision");
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
    public Map<EntityType, List<AbstractEntity>> getEntities() {
        return this.entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final EntityMovement movement, final int playerId) {
        Player player1 = (Player) this.entities.get(EntityType.PLAYER).get(playerId);
        Sword sword1 = (Sword) player1.getWeapon().get();
        if (movement != EntityMovement.STAY_STILL_LEFT && movement != EntityMovement.STAY_STILL_RIGHT) {
            player1.move(movement);
        }
        System.out.println("posizione player" + playerId + " " + player1.getPosition());
        System.out.println("posizione sword" + playerId + " " + sword1.getPosition());
        System.out.println("differenza x " + (player1.getPosition().getRight() - sword1.getPosition().getRight()));
        System.out.println("differenza y " + (player1.getPosition().getLeft() - sword1.getPosition().getLeft()));
        //System.out.println("dimensione " + playerId + " " + player1.getDimension());
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
        Player player = (Player) this.entities.get(EntityType.PLAYER).get(swordId);
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
        final EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        final Player playerL = entityFactory.createPlayer(PLAYER_WIDTH, PLAYER_HEIGHT, new MutablePair<Double, Double>(-PLAYER_X_POSITIOON, 
                PLAYER_Y_POSITIOON), EntityDirection.RIGHT);
        final Player playerR = entityFactory.createPlayer(PLAYER_WIDTH, PLAYER_HEIGHT, new MutablePair<>(PLAYER_X_POSITIOON, 
                PLAYER_Y_POSITIOON), EntityDirection.LEFT);
        playerL.changeEntityState(EntityState.STAYING_STILL);
        playerR.changeEntityState(EntityState.STAYING_STILL);
        entities.put(EntityType.PLAYER, Stream.of(playerL, playerR).collect(Collectors.toList()));
        entities.put(EntityType.SWORD, Stream.of((Sword) entityFactory.createSword(SWORD_WIDTH, SWORD_HEIGHT, new MutablePair<>(
                SWORD_X_POSITIOON, SWORD_Y_POSITIOON), playerL, EntityDirection.RIGHT), 
                (Sword) entityFactory.createSword(SWORD_WIDTH, SWORD_HEIGHT, new MutablePair<>(
                                -SWORD_X_POSITIOON, SWORD_Y_POSITIOON), playerR, EntityDirection.LEFT))
                .collect(Collectors.toList()));
       entities.put(EntityType.PLATFORM, Stream.of((Platform) entityFactory.createPlatform(PLATFORM_WIDTH, PLATFORM_HEIGHT, new MutablePair<>(
               PLATFORM_X_POSITIOON, PLATFORM_Y_POSITIOON))).collect(Collectors.toList()));
       entities.put(EntityType.DOOR, (Stream.of((Door) entityFactory.createDoor(DOOR_WIDTH, DOOR_HEIGHT, new MutablePair<>(
               -DOOR_X_POSITIOON, DOOR_Y_POSITIOON), playerL), (Door) entityFactory.createDoor(DOOR_WIDTH, DOOR_HEIGHT, new MutablePair<>(
                       DOOR_X_POSITIOON, DOOR_Y_POSITIOON), playerR)).collect(Collectors.toList())));
       this.bodyAssociations.setEntities(entities);
       this.rooms.get(this.currentRoom).setEntities(entities);
    }

    @Override
    public void changeGuard(PlayerID player) {
        Player p = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        p.changeGuard();
    }

    @Override
    public void jumpPlayer(final PlayerID player) {
        Player p = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        if (p.getCurrentDirection().equals(EntityDirection.LEFT)) {
            movePlayer(EntityMovement.JUMP_UP_LEFT, player.getID());
        } else {
            movePlayer(EntityMovement.JUMP_UP_RIGHT, player.getID());
        }
    }

    @Override
    public void movePlayerLeft(final PlayerID player) {
        movePlayer(EntityMovement.MOVE_LEFT, player.getID());
    }

    @Override
    public void movePlayerRight(final PlayerID player) {
        
        movePlayer(EntityMovement.MOVE_RIGHT, player.getID());
    }

    @Override
    public void throwSword(final PlayerID player) {
        Player p = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        if (p.getCurrentDirection().equals(EntityDirection.LEFT)) {
            moveSword(EntityMovement.THROW_LEFT, player.getID());
        } else {
            moveSword(EntityMovement.THROW_RIGHT, player.getID());
        }
    }
}

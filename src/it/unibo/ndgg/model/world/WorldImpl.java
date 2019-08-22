package it.unibo.ndgg.model.world;

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
import it.unibo.ndgg.model.entity.entitydynamic.SwordGuard;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.entity.entitystatic.Platform;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesFactoryImpl;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.view.entitydraw.dynamic.SoundsTypes;

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
    private static final double PLATFORM_X_POSITIOON = 0.0;
    private static final double PLATFORM_Y_POSITIOON = -3.6;
    private static final double DOOR_X_POSITIOON = 7.0;
    private static final double DOOR_Y_POSITIOON = -1.65; 
    private static final int NUMBER_OF_ROOMS = 3;
    private int currentRoom;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private BodyAssociations bodyAssociations;
    private Map<EntityType, List<AbstractEntity>> entities;
    private GameState currentGameState;

    /**
     * Creates the World that manages all the entities and rooms. 
     */
    public WorldImpl() {
        this.currentGameState = GameState.IS_GOING;
        this.entities = new HashMap<>();
        this.currentRoom = NUMBER_OF_ROOMS / 2;
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesFactory = new BodyPropertiesFactoryImpl();
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
        this.bodyPropertiesWorld.update();
        this.checkPlayerState();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void notifyCollision(final CollisionResult collisionResult, final Player player) {
        switch (collisionResult) {
            case PLAYERKILLED:
               if (player.getCurrentDirection() == EntityDirection.LEFT) {
                   createBodyProperties((Sword) player.getWeapon().get(), Pair.of(
                           player.getPosition().getLeft() - 0.30, player.getPosition().getRight() + PLAYER_HEIGHT));
               } else if (player.getCurrentDirection() == EntityDirection.RIGHT) {
                   createBodyProperties((Sword) player.getWeapon().get(), Pair.of(
                           player.getPosition().getLeft() + 0.30,player.getPosition().getRight() + PLAYER_HEIGHT));   
               }
                player.die();
                this.changeRoom(player);
                System.out.println("1 collision");
                break;
            case DOORTOUCHED:
                SoundsTypes.DOORTOUCHED.getSound().play();
                this.changeRoom(player);
                System.out.println("2 collision");
                break;
            case SWORDPICKEDUP:
                SoundsTypes.SWORDPICKEDUP.getSound();
                destroyBodyProprerties((Sword) player.getWeapon().get());
                System.out.println("3 collision");
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
        if (movement != EntityMovement.STAY_STILL_LEFT && movement != EntityMovement.STAY_STILL_RIGHT) {
            player1.move(movement);
        }
    }

    /**
     * A methods that gets called when {@link CollisionResult.DOORTOUCHED} or {@link CollisionResult.PLAYERKILLED} 
     * happens and the currentRoom needs to change.
     */
    private void changeRoom(final Player playerWhoOpenedTheDoor) {
        Player playerL = (Player) this.entities.get(EntityType.PLAYER).get(0);
        Player playerR = (Player) this.entities.get(EntityType.PLAYER).get(1);

        if (playerWhoOpenedTheDoor.equals(playerL) || (playerL.isAlive() && !playerR.isAlive())) {
            if (this.currentRoom == 0) {
                this.currentGameState = GameState.PLAYERR_WON;
            } else {
                this.currentRoom--;
                resetRoomToInitialCondition();
            }
        } else if (playerWhoOpenedTheDoor.equals(playerR) || (!playerL.isAlive() && playerR.isAlive())) {
            if (this.currentRoom == WorldImpl.NUMBER_OF_ROOMS - 1) {
                this.currentGameState = GameState.PLAYERL_WON;
            } else {
                this.currentRoom++;
                resetRoomToInitialCondition();
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
        entities.put(EntityType.SWORD, Stream.of((Sword) entityFactory.createSword(playerL, EntityDirection.RIGHT), 
                (Sword) entityFactory.createSword(playerR, EntityDirection.LEFT)).collect(Collectors.toList()));
       entities.put(EntityType.PLATFORM, Stream.of((Platform) entityFactory.createPlatform(PLATFORM_WIDTH, PLATFORM_HEIGHT, new MutablePair<>(
               PLATFORM_X_POSITIOON, PLATFORM_Y_POSITIOON))).collect(Collectors.toList()));
       entities.put(EntityType.DOOR, (Stream.of((Door) entityFactory.createDoor(DOOR_WIDTH, DOOR_HEIGHT, new MutablePair<>(
               -DOOR_X_POSITIOON, DOOR_Y_POSITIOON), playerR), (Door) entityFactory.createDoor(DOOR_WIDTH, DOOR_HEIGHT, new MutablePair<>(
                       DOOR_X_POSITIOON, DOOR_Y_POSITIOON), playerL)).collect(Collectors.toList())));
       this.bodyAssociations.setEntities(entities);
    }

    /**
     * Creates the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} of a {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}.
     * @param sword the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword} that will have the 
     *          {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} created
     * @param height the height of the body
     * @param width the width of the body
     * @param position the position of the body in the {@link World}
     */
    private void createBodyProperties(final Sword sword, final Pair<Double, Double> position) {
        sword.addBodyProperties(this.bodyPropertiesFactory.createDynamicBodyProperties(position, SWORD_WIDTH, SWORD_HEIGHT, EntityType.SWORD));
    }

    /**
     * Destroy the {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} from the 
     * {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}.
     * @param sword the {@link it.unibo.ndgg.model.entity.entitydynamic.Sword} that will have its 
     * {@link it.unibo.ndgg.model.physic.body.DynamicBodyProperties} destroyed
     */
    private void destroyBodyProprerties(final Sword sword) {
        if (sword.bodyProperiesExist()) {
            this.bodyPropertiesWorld.getWorld().removeBody(sword.getBody().getPhysicalBody());
            sword.removeBodyProperties();
        }
    }

    @Override
    public void changeGuard(final PlayerID player) {
        Player playerChangeGuard = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        Player otherPlayer = (Player) this.entities.get(EntityType.PLAYER).stream().filter(i -> i.equals(playerChangeGuard)).findFirst().get();

        if (playerChangeGuard.getWeapon().isPresent()) {
            if (this.checkProximity(playerChangeGuard.getPosition(), otherPlayer.getPosition())) {
                if (otherPlayer.getWeapon().isPresent() && this.checkDirections(playerChangeGuard, otherPlayer)) {
                    if (playerChangeGuard.getSwordGuard().get() != otherPlayer.getSwordGuard().get()) {
                        if (playerChangeGuard.getSwordGuard().get() == SwordGuard.LOW) {
                            if (playerChangeGuard.getCurrentDirection() == EntityDirection.RIGHT) {
                                createBodyProperties((Sword) otherPlayer.getWeapon().get(), Pair.of(otherPlayer.getPosition().getLeft() + 0.30,
                                        otherPlayer.getPosition().getRight() + PLAYER_HEIGHT));
                                SoundsTypes.PLAYERDISARMED.getSound().play();
                                otherPlayer.dropWeapon(EntityMovement.DROP_RIGHT);
                            } else {
                                createBodyProperties((Sword) otherPlayer.getWeapon().get(), Pair.of(otherPlayer.getPosition().getLeft() - 0.30,
                                        otherPlayer.getPosition().getRight() + PLAYER_HEIGHT));
                                SoundsTypes.PLAYERDISARMED.getSound().play();
                                otherPlayer.dropWeapon(EntityMovement.DROP_LEFT);
                            }
                            playerChangeGuard.changeGuard();
                        } else {
                            if (otherPlayer.getCurrentDirection() == EntityDirection.RIGHT) {
                                createBodyProperties((Sword) playerChangeGuard.getWeapon().get(), Pair.of(playerChangeGuard.getPosition().getLeft() + 0.30,
                                        playerChangeGuard.getPosition().getRight() + PLAYER_HEIGHT));
                                SoundsTypes.PLAYERDISARMED.getSound().play();
                                playerChangeGuard.dropWeapon(EntityMovement.DROP_RIGHT);
                            } else {
                                createBodyProperties((Sword) playerChangeGuard.getWeapon().get(), Pair.of(playerChangeGuard.getPosition().getLeft() - 0.30,
                                        playerChangeGuard.getPosition().getRight() + PLAYER_HEIGHT));
                                SoundsTypes.PLAYERDISARMED.getSound().play();
                                playerChangeGuard.dropWeapon(EntityMovement.DROP_LEFT);
                            }
                        }
                    }
                }
            }
            playerChangeGuard.changeGuard(); 
        }
    }

    public void jumpPlayer(final PlayerID player) {
        Player p = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        if (p.getState() != EntityState.JUMPING_UP) {
            if (p.getCurrentDirection().equals(EntityDirection.LEFT)) {
                SoundsTypes.JUMP.getSound().play();
                movePlayer(EntityMovement.JUMP_UP_LEFT, player.getID());
            } else {
                SoundsTypes.JUMP.getSound().play();
                movePlayer(EntityMovement.JUMP_UP_RIGHT, player.getID());
    
            }
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

    /**
     * 
     * @param player
     */
    public void attackPlayer(final PlayerID player) {
        Player playerWhoAttack = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        Player loserPlayer = (Player) this.entities.get(EntityType.PLAYER).stream().filter(i -> i.equals(playerWhoAttack)).findFirst().get();
        SoundsTypes.ATTACK.getSound().play();
        System.out.println(" X player attaccante " + Double.toString(playerWhoAttack.getPosition().getLeft()) + 
                " Y player attaccante " + Double.toString(playerWhoAttack.getPosition().getRight()) + 
                " X player attaccato " + Double.toString(loserPlayer.getPosition().getLeft()) + 
                " Y player attaccato " + Double.toString(loserPlayer.getPosition().getRight()));
        if (playerWhoAttack.getWeapon().isPresent()) {
            if (this.checkProximity(playerWhoAttack.getPosition(), loserPlayer.getPosition()) 
                    && checkDirectionToAttack(playerWhoAttack, loserPlayer)) {
                if (!loserPlayer.getWeapon().isPresent()) {
                    loserPlayer.die();
                } else {
                    if (loserPlayer.getSwordGuard().get() != loserPlayer.getSwordGuard().get()) {
                        loserPlayer.die();
                    }
                }
            }
        }
    }

    @Override
    public void throwSword(final PlayerID player) {
        Player p = (Player) this.entities.get(EntityType.PLAYER).get(player.getID());
        if (p.getState() == EntityState.STAYING_STILL && p.getWeapon().isPresent()) {
        if (p.getCurrentDirection().equals(EntityDirection.LEFT)) {
            createBodyProperties((Sword)p.getWeapon().get(),Pair.of(p.getPosition().getLeft() + 0.30,p.getPosition().getRight() + PLAYER_HEIGHT));
            p.dropWeapon(EntityMovement.THROW_LEFT);
        } else {
            createBodyProperties((Sword)p.getWeapon().get(),Pair.of(p.getPosition().getLeft() - 0.30,p.getPosition().getRight() + PLAYER_HEIGHT));
            p.dropWeapon(EntityMovement.THROW_LEFT);
        }
        SoundsTypes.THROW.getSound().play();
        }
    }

    private void checkPlayerState() {
        Player p1 = (Player) this.entities.get(EntityType.PLAYER).get(PlayerID.FIRST_PLAYER.getID());
        if (p1.getBody().getPhysicalBody().isAsleep()){
            p1.changeEntityState(EntityState.STAYING_STILL);
        }
        Player p2 = (Player) this.entities.get(EntityType.PLAYER).get(PlayerID.SECOND_PLAYER.getID());
        if (p2.getBody().getPhysicalBody().isAsleep()){
            p2.changeEntityState(EntityState.STAYING_STILL);
        }
    }

    private boolean checkProximity(final Pair<Double, Double> positionFirstPlayer, final Pair<Double, Double> positionSecondPlayer) {
        return Math.abs(positionFirstPlayer.getLeft() - positionSecondPlayer.getLeft()) <= SWORD_WIDTH
                && Math.abs(positionFirstPlayer.getRight() - positionSecondPlayer.getRight()) <= PLAYER_HEIGHT/2;
    }

    private boolean checkDirections(final Player player1, final Player player2) {
        return (player1.getCurrentDirection() == EntityDirection.RIGHT 
                && player2.getCurrentDirection() == EntityDirection.LEFT
                && player1.getPosition().getLeft() < player2.getPosition().getLeft())
                || (player2.getCurrentDirection() == EntityDirection.RIGHT 
                && player1.getCurrentDirection() == EntityDirection.LEFT
                && player2.getPosition().getLeft() < player1.getPosition().getLeft());
    }

    private boolean checkDirectionToAttack(final Player player1, final Player player2) {
        return (player1.getCurrentDirection() == EntityDirection.RIGHT 
                && player1.getPosition().getLeft() < player2.getPosition().getLeft())
                || (player1.getCurrentDirection() == EntityDirection.LEFT 
                    && player2.getPosition().getLeft() < player1.getPosition().getLeft());
    }

    private void resetRoomToInitialCondition() {
        this.entities.get(EntityType.DOOR).stream().map(d -> (Door) d).forEach(door -> door.resetIsHit());
        this.entities.get(EntityType.PLAYER).stream().map(p -> (Player) p).forEach(player -> {
            player.changeEntityState(EntityState.STAYING_STILL);
            this.entities.get(EntityType.SWORD).stream().map(s -> (Sword) s).forEach(sword -> {
               if(sword.getState() != EntityState.EQUIPPED) {
                   destroyBodyProprerties(sword);
                   player.equipWeapon(sword);
               }
            });
        });
        this.entities.get(EntityType.PLAYER).get(0).getBody().
        getPhysicalBody().translate(-(this.entities.get(EntityType.PLAYER).get(0).getPosition().getLeft())-PLAYER_X_POSITIOON,
                -(this.entities.get(EntityType.PLAYER).get(0).getPosition().getRight())+ PLAYER_Y_POSITIOON);
        this.entities.get(EntityType.PLAYER).get(1).getBody().
        getPhysicalBody().translate(-(this.entities.get(EntityType.PLAYER).get(1).getPosition().getLeft())+PLAYER_X_POSITIOON,
                -(this.entities.get(EntityType.PLAYER).get(1).getPosition().getRight())+ PLAYER_Y_POSITIOON);
    }

}

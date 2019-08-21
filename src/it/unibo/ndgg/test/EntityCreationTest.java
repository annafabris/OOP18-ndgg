package it.unibo.ndgg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.ndgg.model.entity.AbstractEntity;
import it.unibo.ndgg.model.entity.EntityDirection;
import it.unibo.ndgg.model.entity.EntityFactory;
import it.unibo.ndgg.model.entity.EntityFactoryImpl;
import it.unibo.ndgg.model.entity.EntityMovement;
import it.unibo.ndgg.model.entity.EntityState;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.entity.entitydynamic.SwordGuard;
import it.unibo.ndgg.model.entity.entitystatic.Door;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * This test creates entities.
 */
public class EntityCreationTest {

    private static final double WORLD_WIDTH = 960;
    private static final double WORLD_HEIGHT = 450;
    private static final double DOOR_X_POSITIOON = 7.0;
    private static final double DOOR_Y_POSITIOON = -1.65;
    private static final double DOOR_HEIGHT = 1.6;
    private static final double DOOR_WIDTH = 1.8;
    private WorldImpl world;
    private BodyPropertiesFactory bodyPropertiesFactory = new BodyPropertiesFactory();
    private Map<EntityType, List<AbstractEntity>> entities;
    private BodyAssociations bodyAssociations;
    private Player playerR;
    private Player playerL;

    /**
     * This creates all entities.
     */
    public EntityCreationTest() {
        this.world = new WorldImpl();
        this.bodyAssociations = new BodyAssociations();
        BodyPropertiesWorld bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this.world, WORLD_WIDTH, WORLD_HEIGHT, bodyAssociations);
        EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        playerR = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(1.0, 0.0), EntityDirection.LEFT);
        playerL = entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(-1.0, 0.0), EntityDirection.RIGHT);
        this.entities = new HashMap<>();
        this.entities.put(EntityType.PLAYER, Stream.of(playerR, playerL).collect(Collectors.toList()));
        this.entities.put(EntityType.SWORD, Stream.of(
                         entityFactory.createSword(playerR, playerR.getCurrentDirection()), 
                         entityFactory.createSword(playerL, playerL.getCurrentDirection()))
                .collect(Collectors.toList()));
        entities.put(EntityType.DOOR, (Stream.of(
                         entityFactory.createDoor(DOOR_WIDTH, DOOR_HEIGHT, new MutablePair<>(-DOOR_X_POSITIOON, DOOR_Y_POSITIOON), playerL), 
                         entityFactory.createDoor(DOOR_WIDTH, DOOR_HEIGHT, new MutablePair<>(DOOR_X_POSITIOON, DOOR_Y_POSITIOON), playerR))
                .collect(Collectors.toList())));
        //this.entities.put(EntityType.PLATFORM, Stream.of( entityFactory.c))
        this.bodyAssociations.setEntities(this.entities);
    }

    /**
     * Test the first condition of both sword and player.
     */
    @Test
    public void testFirstCondition() {
        assertEquals(EntityState.STAYING_STILL, this.playerL.getState());
        assertEquals(EntityState.STAYING_STILL, this.playerR.getState());
        assertTrue(((Player) this.entities.get(EntityType.PLAYER).get(0)).getWeapon().isPresent());
        assertTrue(((Player) this.entities.get(EntityType.PLAYER).get(1)).getWeapon().isPresent());
        assertEquals(EntityState.EQUIPPED, ((Sword) this.entities.get(EntityType.SWORD).get(0)).getState());
        assertEquals(EntityState.EQUIPPED, ((Sword) this.entities.get(EntityType.SWORD).get(1)).getState());
        assertFalse(((Sword) this.entities.get(EntityType.SWORD).get(0)).bodyProperiesExist());
        assertFalse(((Sword) this.entities.get(EntityType.SWORD).get(1)).bodyProperiesExist());
        assertTrue(this.entities.get(EntityType.PLAYER).get(0).isAlive());
        assertTrue(this.entities.get(EntityType.PLAYER).get(1).isAlive());
    }
    /**
     * A general test about the creation of entities.
     */
    @Test
    public void testEntityCreation() {
        assertEquals(1, this.entities.get(EntityType.PLAYER).stream().filter(i -> i.getBody() == playerR.getBody()).count());
        assertEquals(1, this.entities.get(EntityType.PLAYER).stream().filter(i -> i.getBody() == playerL.getBody()).count());
        assertEquals(2, this.entities.get(EntityType.PLAYER).stream().count());
        assertEquals(2, this.entities.get(EntityType.SWORD).stream().count());
        this.playerL.changeEntityState(EntityState.MOVING);
        assertEquals(EntityState.MOVING, this.playerL.getState());
        assertEquals(EntityState.STAYING_STILL, this.playerR.getState());
        assertEquals(SwordGuard.LOW, this.playerL.getSwordGuard().get());
    }

    /**
     * A general test association with sword and player.
     */
    @Test
    public void testAssociationSwordPlayer() {
        assertTrue(this.playerL.getCurrentDirection() != this.playerR.getCurrentDirection());
        assertTrue(this.playerL.getWeapon().isPresent());
        assertTrue(this.playerR.getWeapon().isPresent());
        assertEquals(this.playerR.getCurrentDirection(), ((Sword) this.entities.get(EntityType.SWORD).get(0)).getCurrentDirection());
        assertEquals(this.playerL.getCurrentDirection(), ((Sword) this.entities.get(EntityType.SWORD).get(1)).getCurrentDirection());
        assertEquals(EntityState.EQUIPPED, ((Sword) this.entities.get(EntityType.SWORD).get(0)).getState());
    }

    /**
     * This test looks the change of state.
     */
    //@Test
    public void testChangeStatePlayer() {
        this.playerL.move(EntityMovement.JUMP_UP_RIGHT);
        assertEquals(EntityState.JUMPING_UP, this.playerL.getState());
        this.playerL.changeEntityState(EntityState.STAYING_STILL);
        assertEquals(EntityState.STAYING_STILL, this.playerL.getState());
        this.playerL.move(EntityMovement.JUMP_UP_LEFT);
        assertEquals(EntityState.STAYING_STILL, this.playerL.getState());
        this.playerL.move(EntityMovement.MOVE_LEFT);
        assertEquals(EntityState.MOVING, this.playerL.getState());
    }

    /**
     * This test looks the door and the player.
     */
    @Test
    public void testAssociationDoorAndPlayer() {
        assertEquals(this.playerL, ((Door) this.entities.get(EntityType.DOOR).get(0)).getPlayerWhoCanOpen());
        assertEquals(this.playerR, ((Door) this.entities.get(EntityType.DOOR).get(1)).getPlayerWhoCanOpen());
        assertFalse(((Door) this.entities.get(EntityType.DOOR).get(1)).getDoorStatus());
        assertFalse(((Door) this.entities.get(EntityType.DOOR).get(0)).getDoorStatus());
        ((Door) this.entities.get(EntityType.DOOR).get(1)).hit();
        assertTrue(((Door) this.entities.get(EntityType.DOOR).get(1)).getDoorStatus());
        assertTrue((this.playerL).equals(((Door) this.entities.get(EntityType.DOOR).get(0)).getPlayerWhoCanOpen()));
        assertFalse((this.playerR).equals(((Door) this.entities.get(EntityType.DOOR).get(0)).getPlayerWhoCanOpen()));
    }
}

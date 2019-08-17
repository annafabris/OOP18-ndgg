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
import org.apache.commons.lang3.tuple.Pair;
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
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 *
 */
public class EntityCreationTest {

    private static final double WORLD_WIDTH = 960;
    private static final double WORLD_HEIGHT = 450;
    private static final Pair<Double, Double> SWORD1_POSITION = new MutablePair<>(1.0, 3.0);
    private static final Pair<Double, Double> SWORD2_POSITION = new MutablePair<>(1.0, 4.0);
    private static final Double SWORD_HEIGHT = 0.5;
    private static final Double SWORD_WIDTH = 0.5;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private WorldImpl world;
    private BodyPropertiesFactory bodyPropertiesFactory = new BodyPropertiesFactory();
    private Map<EntityType, List<AbstractEntity>> entities;
    private BodyAssociations bodyAssociations;
    private Player playerR;
    private Player playerL;

    public EntityCreationTest() {
        this.world = new WorldImpl(Pair.of(1360.0, 500.0));
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createBodyPropertiesWorld(this.world, WORLD_WIDTH, WORLD_HEIGHT, bodyAssociations);
        EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        playerR = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(1.0, 0.0), EntityDirection.LEFT);
        playerL = entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(-1.0, 0.0), EntityDirection.RIGHT);
        this.entities = new HashMap<>();
        this.entities.put(EntityType.PLAYER, Stream.of(playerR, playerL).collect(Collectors.toList()));
        this.entities.put(EntityType.SWORD, Stream.of(
                         entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, SWORD1_POSITION, playerR, playerR.getCurrentDirection()), 
                         entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, SWORD2_POSITION, playerL, playerL.getCurrentDirection()))
                .collect(Collectors.toList()));
        //this.entities.put(EntityType.PLATFORM, Stream.of( entityFactory.c))
        this.bodyAssociations.setEntities(this.entities);
    }

    /**
     * A general test about the creation of entities.
     */
    @Test
    public void testEntityCreation() {
        assertEquals(EntityState.STAYING_STILL, this.playerL.getState());
        assertEquals(1, this.entities.get(EntityType.PLAYER).stream().filter(i -> i.getBody() == playerR.getBody()).count());
        assertEquals(1, this.entities.get(EntityType.PLAYER).stream().filter(i -> i.getBody() == playerL.getBody()).count());
        assertEquals(2, this.entities.get(EntityType.PLAYER).stream().count());
        assertEquals(2, this.entities.get(EntityType.SWORD).stream().count());
        assertEquals(SWORD1_POSITION, this.entities.get(EntityType.SWORD).get(0).getPosition());
        assertEquals(SWORD2_POSITION, this.entities.get(EntityType.SWORD).get(1).getPosition());
        assertEquals(new MutablePair<Double, Double>(SWORD_HEIGHT, SWORD_WIDTH), 
                this.entities.get(EntityType.SWORD).get(0).getDimension());
        assertEquals(new MutablePair<Double, Double>(SWORD_HEIGHT, SWORD_WIDTH), 
                this.entities.get(EntityType.SWORD).get(1).getDimension());
        assertTrue(this.entities.get(EntityType.SWORD).get(0).isAlive());
        assertTrue(this.entities.get(EntityType.SWORD).get(1).isAlive());
        assertTrue(this.entities.get(EntityType.PLAYER).get(0).isAlive());
        assertTrue(this.entities.get(EntityType.PLAYER).get(1).isAlive());
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
        this.playerL.dropWeapon(EntityMovement.DROP_RIGHT);
        assertFalse(this.playerL.getWeapon().isPresent());
        assertFalse(((Sword) this.entities.get(EntityType.SWORD).get(1)).getPlayer().isPresent());
        assertEquals(EntityState.DROPPING, ((Sword) this.entities.get(EntityType.SWORD).get(1)).getState());
        assertTrue(this.playerR.getWeapon().isPresent());
        assertTrue(((Sword) this.entities.get(EntityType.SWORD).get(0)).getPlayer().isPresent());
        this.playerL.equipWeapon((Sword) this.entities.get(EntityType.SWORD).get(1));
        assertTrue(this.playerL.getWeapon().isPresent());
        assertTrue(((Sword) this.entities.get(EntityType.SWORD).get(1)).getPlayer().isPresent());
        assertEquals(EntityState.EQUIPPED, ((Sword) this.entities.get(EntityType.SWORD).get(0)).getState());
        this.playerL.dropWeapon(EntityMovement.DROP_RIGHT);
        assertFalse(this.playerL.getWeapon().isPresent());
        this.playerL.die();
        assertFalse(this.playerL.getWeapon().isPresent());
        assertFalse(((Sword) this.entities.get(EntityType.SWORD).get(1)).getPlayer().isPresent());
        assertTrue(((Sword) this.entities.get(EntityType.SWORD).get(0)).getPlayer().isPresent());
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
     * This test looks the change of state.
     */
    @Test
    public void testGuard() {
        assertEquals(SwordGuard.LOW, this.playerL.getSwordGuard().get());
        this.playerL.changeGuard();
        assertEquals(SwordGuard.HIGH, this.playerL.getSwordGuard().get());
        this.playerL.dropWeapon(EntityMovement.DROP_LEFT);
        assertFalse(this.playerL.getSwordGuard().isPresent());
        this.playerL.equipWeapon((Sword) this.entities.get(EntityType.SWORD).get(1));
        assertEquals(SwordGuard.LOW, this.playerL.getSwordGuard().get());
    }
}

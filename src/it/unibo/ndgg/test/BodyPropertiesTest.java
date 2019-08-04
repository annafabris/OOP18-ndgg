package it.unibo.ndgg.test;

import static org.junit.Assert.assertEquals;
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
import it.unibo.ndgg.model.entity.EntityFactory;
import it.unibo.ndgg.model.entity.EntityFactoryImpl;
import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.entity.entitydynamic.Player;
import it.unibo.ndgg.model.entity.entitydynamic.Sword;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.physic.BodyPropertiesWorld;
import it.unibo.ndgg.model.world.WorldImpl;


public class BodyPropertiesTest {

    private final Pair<Double, Double> SWORD1_POSITION = new MutablePair<>(1.0, 3.0);
    private final Pair<Double, Double> SWORD2_POSITION = new MutablePair<>(1.0, 4.0);
    private final Double SWORD_HEIGHT = 0.5;
    private final Double SWORD_WIDTH = 0.5;
    private BodyPropertiesWorld bodyPropertiesWorld;
    private WorldImpl world;
    private BodyPropertiesFactory bodyPropertiesFactory = new BodyPropertiesFactory();
    private Map<EntityType, List<AbstractEntity>> entities;
    private BodyAssociations bodyAssociations;
    private Player playerR;
    private Player playerL;

    public BodyPropertiesTest() {
        this.world = new WorldImpl();
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesWorld = this.bodyPropertiesFactory.createPhysicalWorld(2.0, 2.0, bodyAssociations);
        EntityFactory entityFactory = new EntityFactoryImpl(this.bodyPropertiesFactory);
        playerR = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(1.0, 0.0));
        playerL = entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(-1.0, 0.0));
        this.entities = new HashMap<>();
        this.entities.put(EntityType.PLAYER, Stream.of(playerR, playerL).collect(Collectors.toList()));
        this.entities.put(EntityType.SWORD, Stream.of(
                (Sword) entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, SWORD1_POSITION, playerR), 
                (Sword) entityFactory.createSword(SWORD_HEIGHT, SWORD_WIDTH, SWORD2_POSITION, playerL))
                .collect(Collectors.toList()));
        this.bodyAssociations.setEntities(this.entities);
    }

    /**
     * A general test about the vreation of entities
     */
    @Test
    public void testEntityCreation() {
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
}

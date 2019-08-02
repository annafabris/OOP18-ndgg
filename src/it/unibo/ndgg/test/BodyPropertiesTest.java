package it.unibo.ndgg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.MutablePair;
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
import it.unibo.ndgg.model.physic.BodyPropertiesWorldImpl;
import it.unibo.ndgg.model.world.WorldImpl;


public class BodyPropertiesTest {

    private BodyPropertiesWorld bodyPropertiesWorld;
    private WorldImpl world;
    private BodyPropertiesFactory bodyPropertiesFactory;
    private Map<EntityType, List<AbstractEntity>> entities;
    private BodyAssociations bodyAssociations;
    private Player playerR;
    private Player playerL;


    public BodyPropertiesTest() {
        this.world = new WorldImpl();
        this.bodyAssociations = new BodyAssociations();
        this.bodyPropertiesWorld = new BodyPropertiesFactory().createPhysicalWorld(100, 100, this.bodyAssociations);
        EntityFactory entityFactory = new EntityFactoryImpl();
        playerR = entityFactory.createPlayer(100.0, 100.0, new MutablePair<Double, Double>(1.0, 0.0));
        playerL = entityFactory.createPlayer(100.0, 100.0, new MutablePair<>(-1.0, 0.0));
        this.entities.put(EntityType.PLAYER, Stream.of(playerR, playerL).collect(Collectors.toList()));
        this.entities.put(EntityType.SWORD, Stream.of(
                (Sword) entityFactory.createSword(2.0, 40.0, new MutablePair<>(1.0, 5.0), playerR), 
                (Sword) entityFactory.createSword(2.0, 40.0, new MutablePair<>(1.0, 5.0), playerL))
                .collect(Collectors.toList()));
        this.bodyAssociations.setEntities(this.entities);
    }

    @Test
    public void testEntity() {
        assertEquals(1, this.entities.get(EntityType.PLAYER).stream().filter(i -> i.getBody() == playerR.getBody()).count());
    }
}

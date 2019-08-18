package it.unibo.ndgg.test;

import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.BodyAssociations;
import it.unibo.ndgg.model.physic.BodyPropertiesFactory;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * Adjunctive tests over the creation of {@link it.unibo.jmpcoon.model.physics.PhysicalBody}s not covered into the
 * {@link StaticPhysicalBodyCreationTest} and the {@link DynamicPhysicalBodyCreationTest} tests.
 */
public class BodyPropertiesCreationTest {
    private static final double WORLD_WIDTH = 960;
    private static final double WORLD_HEIGHT = 450;
    private static final double TEST_WIDTH = WORLD_WIDTH / 20;
    private static final double TEST_HEIGHT = WORLD_HEIGHT / 10;
    private static final MutablePair<Double, Double> TEST_POSITION = new MutablePair<>(WORLD_WIDTH / 3, WORLD_HEIGHT / 3);

    private final BodyPropertiesFactory factory;

    public BodyPropertiesCreationTest() {
        BodyAssociations bodyAssociations = new BodyAssociations();
        this.factory = new BodyPropertiesFactory();
        this.factory.createBodyPropertiesWorld(new WorldImpl(new MutablePair<>(WORLD_WIDTH, WORLD_HEIGHT)), WORLD_WIDTH, WORLD_HEIGHT, bodyAssociations);
    }

    /**
     * Test for the correct creation of a {@link it.unibo.ndgg.model.entity.entitydynamic.Player} and 
     * {@link it.unibo.ndgg.model.entity.entitydynamic.Sword}.
     */
    @Test
    public void dynamicBodyPropertiesCreationTest() {
        this.factory.createDynamicBodyProperties(TEST_POSITION, TEST_WIDTH, TEST_HEIGHT, EntityType.PLAYER);
        this.factory.createDynamicBodyProperties(TEST_POSITION, TEST_WIDTH, TEST_HEIGHT, EntityType.SWORD);
    }

    /**
     * Test for the correct creation of a {@link it.unibo.ndgg.model.entity.entitystatic.Door} and 
     * {@link it.unibo.ndgg.model.entity.entitystatic.Platform}.
     */
    @Test
    public void staticBodyPropertiesCreationTest() {
        this.factory.createStaticBodyProperties(TEST_POSITION, TEST_WIDTH, TEST_HEIGHT, EntityType.DOOR);
        this.factory.createStaticBodyProperties(TEST_POSITION, TEST_WIDTH, TEST_HEIGHT, EntityType.PLATFORM);
    }

    /**
     * Test for the failure of the creation of {@link it.unibo.ndgg.model.entity.entitystatic.Door} which is not a Dynamic Body.
     */
    @Test(expected = IllegalStateException.class)
    public void dynamicBodyPropertiesTypeTest() {
        this.factory.createDynamicBodyProperties(TEST_POSITION, TEST_WIDTH, TEST_HEIGHT, EntityType.DOOR);
    }

    /**
     * Test for the failure of the creation of {@link it.unibo.ndgg.model.entity.entitydynamic.Player} which is not a Static Body.
     */
    @Test(expected = IllegalStateException.class)
    public void staticBodyPropertiesTypeTest() {
        this.factory.createStaticBodyProperties(TEST_POSITION, TEST_WIDTH, TEST_HEIGHT, EntityType.PLAYER);
    }
}


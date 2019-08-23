package it.unibo.ndgg.model.physic;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.CategoryFilter;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.DynamicBodyProperties;
import it.unibo.ndgg.model.physic.body.StaticBodyProperties;
import it.unibo.ndgg.model.world.WorldImpl;

/**
 * {@inheritDoc}.
 */
public class BodyPropertiesFactoryImpl implements BodyPropertiesFactory {

    private static final double PLAYER_DENSITY = 50;
    private static final double PLAYER_FRICTION = 0.25;
    private static final double PLATFORM_FRICTION = 0.8;
    private static final double SWORD_GRAVITYSCALE = 50;
    private static final double SWORD_FRICTION = 50;
    private static final long CATEGORY_PLAYER = 1;      // 000001 binary rapresentation 
    private static final long CATEGORY_DOOR = 2;        // 000010 binary rapresentation 
    private static final long CATEGORY_SWORD = 4;       // 000100 binary rapresentation 
    private static final long CATEGORY_PLATFORM = 8;    // 001000 binary rapresentation 

    private static final CategoryFilter SWORD_FILTER = new CategoryFilter(CATEGORY_SWORD, CATEGORY_SWORD
                                                                                        | CATEGORY_PLAYER
                                                                                        | CATEGORY_PLATFORM
                                                                                        | CATEGORY_DOOR);
    private static final CategoryFilter PLAYER_FILTER = new CategoryFilter(CATEGORY_PLAYER, CATEGORY_SWORD
                                                                                        | CATEGORY_PLAYER
                                                                                        | CATEGORY_DOOR
                                                                                        | CATEGORY_PLATFORM);
    private static final CategoryFilter DOOR_FILTER = new CategoryFilter(CATEGORY_DOOR, CATEGORY_PLAYER
                                                                                        | CATEGORY_SWORD);
    private static final CategoryFilter PLATFORM_FILTER = new CategoryFilter(CATEGORY_PLATFORM, CATEGORY_PLAYER
                                                                                        | CATEGORY_SWORD
                                                                                        | CATEGORY_PLATFORM);

    private BodyPropertiesWorld physicalWorld;
 
    /**
     * {@inheritDoc}
     */
    public BodyPropertiesWorld createBodyPropertiesWorld(final WorldImpl world, final double width, final double height, final BodyAssociations bodyAssociations) {
        this.physicalWorld = new BodyPropertiesWorldImpl(world, new org.dyn4j.dynamics.World(new AxisAlignedBounds(width, height)), bodyAssociations);
        return this.physicalWorld;
    }
 
    /**
     * {@inheritDoc}
     */
    public DynamicBodyProperties createDynamicBodyProperties(final Pair<Double, Double> position, final Double width, 
            final Double height, final EntityType type) {
        final Body body;
        if (type == EntityType.PLAYER) {
            body = createBody(position, width, height, PLAYER_FILTER);
            body.getFixture(0).setFriction(PLAYER_FRICTION);
            body.getFixture(0).setDensity(PLAYER_DENSITY);
            body.setMass(MassType.FIXED_ANGULAR_VELOCITY);
        } else if (type == EntityType.SWORD) {
            body = createBody(position, width, height, SWORD_FILTER);
            body.setGravityScale(SWORD_GRAVITYSCALE);
            body.getFixture(0).setFriction(SWORD_FRICTION);
            //body.getFixture(0).setRestitution(1);
        } else {
            throw new IllegalStateException("Dynamic EntityType Does not exist");
        }
        final DynamicBodyProperties dynamicBody = new DynamicBodyProperties(body, type);
        this.physicalWorld.putPhysicalBodyToBody(dynamicBody, body, type);
        return dynamicBody;
    }

    /**
     * {@inheritDoc}
     */
    public StaticBodyProperties createStaticBodyProperties(final Pair<Double, Double> position, final Double width, 
            final Double height, final EntityType type) {
        CategoryFilter filter;
        final Body body;
        switch (type) {
            case DOOR:
                filter = DOOR_FILTER;
                body = createBody(position, width, height, filter);
                body.setMass(MassType.INFINITE);
                break;
            case PLATFORM:
                filter = PLATFORM_FILTER;
                body = createBody(position, width, height, filter);
                body.setMass(MassType.INFINITE);
                body.getFixture(0).setFriction(PLATFORM_FRICTION);
                break;
            default:
                throw new IllegalStateException("Static EntityType Does not exist");
        }
        final StaticBodyProperties staticBody = new StaticBodyProperties(body);
        this.physicalWorld.putPhysicalBodyToBody(staticBody, body, type);
        return staticBody;
    }

    /**
     * A private method used to create a {@link org.dyn4j.dynamics.Body} and to set its position, width, height and 
     * {@link org.dyn4j.collision.CategoryFilter}.
     * @param position the position to set
     * @param width the width to set
     * @param height the height to set
     * @param filter the  {@link org.dyn4j.collision.CategoryFilter} to set
     * @return the created {@link org.dyn4j.dynamics.Body}
     */
    private Body createBody(final Pair<Double, Double> position, final double width, final double height, final CategoryFilter filter) {
        final Body body = new Body();
        body.addFixture(Geometry.createRectangle(width, height));
        body.translate(new Vector2(position.getLeft(), position.getRight()));
        body.setMass(MassType.FIXED_ANGULAR_VELOCITY);
        body.getFixture(0).setFilter(filter);
        body.getFixture(0).setRestitution(0);
        this.physicalWorld.getWorld().addBody(body);
        return body;
    }

}

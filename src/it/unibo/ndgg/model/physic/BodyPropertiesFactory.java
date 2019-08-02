package it.unibo.ndgg.model.physic;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import it.unibo.ndgg.model.entity.EntityType;
import it.unibo.ndgg.model.physic.body.PlayerBodyProperties;
import it.unibo.ndgg.model.physic.body.SwordBodyProperties;

/**
 * 
 *
 */
public class BodyPropertiesFactory {

    private BodyPropertiesWorld physicalWorld;
    private ImmutablePair<Double, Double> worldDimensions;
 
    /**
     * Create a new {@link World} linked to the {@link BodyPropertiesWorld}.
     * @param width the width of the {@link World}
     * @param height the height of the {@link World}
     * @param bodyAssociations the class that contains all the associations necessary for the collisions to work {@link BodyAssociations}
     * @return {@link BodyPropertiesWorld}
     */
    public BodyPropertiesWorld createPhysicalWorld(final double width, final double height, final BodyAssociations bodyAssociations) {
        this.worldDimensions = new ImmutablePair<>(width, height);
        this.physicalWorld = new BodyPropertiesWorldImpl(new World(new AxisAlignedBounds(width, height)), bodyAssociations); //TODO width, height controllare
        return this.physicalWorld;
    }
 
    /**
     * 
     * @param position
     * @param width
     * @param height
     * @return
     */
    public PlayerBodyProperties createPlayerBodyProperties(final Pair<Double, Double> position, final Double width, final Double height) {
        final Body body = createBody(position, width, height);
        final PlayerBodyProperties playerBody = new PlayerBodyProperties(body);
        this.physicalWorld.putPhysicalBodyToBody(playerBody, body, EntityType.PLAYER);
        return playerBody;
    }

    /**
     * 
     * @param position
     * @param width
     * @param height
     * @return
     */
    public SwordBodyProperties createSwordBodyProperties(Pair<Double, Double> position, Double width, Double height) {
        final Body body = createBody(position, width, height);
        final SwordBodyProperties swordBody = new SwordBodyProperties(body);
        this.physicalWorld.putPhysicalBodyToBody(swordBody, body, EntityType.SWORD);
        return swordBody;
    }

    private Body createBody(final Pair<Double, Double> position, final double width, final double height) {
        final Body body = new Body();
        body.addFixture(Geometry.createRectangle(width, height));
        body.translate(new Vector2(position.getLeft(), position.getRight()));
        body.setMass(MassType.FIXED_ANGULAR_VELOCITY);
        this.physicalWorld.getWorld();
        return body;
    }

}

package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

/**
 * The Jumpable interface is implemented by classes that can be jumped on by the player
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Wall
 * @see game.ground.Tree
 */

public interface Jumpable {

    /**
     * Unimplemented method for classes to inherit.
     * @param actor the actor jumping
     * @param location the location of the jumpable object
     * @return String that describes if the actor succeeds or fails in Jumping onto the object
     */
    String jump(Actor actor, Location location);

    default String jumpMovement(Actor actor, Location location){
        location.map().moveActor(actor, location);
        return actor + " jumps up the " + location.getGround().toString();
    }

    default String jumpFailure(Actor actor, Location location, int damage){
        actor.hurt(damage);
        return actor + " fails to jump the " + location.getGround().toString();
    }

    default String destroy(Actor actor, Location location){
        location.map().moveActor(actor, location);
        location.setGround(new Dirt());
        location.addItem(new Coin(5));

        return actor + " destroys the " + location.getGround().toString();
    }
}

package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

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
}

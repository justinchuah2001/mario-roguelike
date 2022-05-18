package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;

import java.util.Random;

/**
 * The Jumpable interface is implemented by classes that can be jumped on by the player
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Wall
 * @see game.ground.Tree
 */
public interface Jumpable {
  Random r = new Random();

  /**
   * Default method for running jump checks.
   * <p>Destroys Jumpable if actor is INVINCIBLE, bypasses the probability if actor is TALL, and moves or damages
   * the actor based on probability otherwise.</p>
   *
   * @param actor    the actor jumping
   * @param location the location of the jumpable object
   * @return String that describes if the actor succeeds or fails in Jumping onto the object
   * @see Status
   */
  default String jump(Actor actor, Location location) {

    if (actor.hasCapability(Status.INVINCIBLE)) {
      return destroy(actor, location) + getFlavourDestroy() + "A coin appeared!";

    } else if (actor.hasCapability(Status.TALL)) {
      return jumpMovement(actor, location) + " easily! " + getFlavourJump();

    } else if (r.nextInt(100) < getJumpSuccessRate()) {
      return jumpMovement(actor, location) + "! " + getFlavourJump();

    } else {
      String message = jumpFailure(actor, location, getFallDamage()) + getFlavourFail();
      if (!actor.isConscious()) {
        location.map().removeActor(actor);
      }
      return message;
    }
  }

  /**
   * Generic code block to move the actor onto the jumpable object and returns a generic message
   *
   * @param actor    the actor jumping
   * @param location the location of the jumpable object
   * @return Generic success message of the actor succeeding a jump check
   */
  default String jumpMovement(Actor actor, Location location) {
    location.map().moveActor(actor, location);
    return actor + " jumps up the " + location.getGround().toString();
  }

  /**
   * Generic code block to damage the actor from a failed jump attempt and returns a generic message
   *
   * @param actor    the actor jumping
   * @param location the location of the jumpable object
   * @param damage   the amount of fall damage taken
   * @return Generic failure message of the actor failing a jump check
   */
  default String jumpFailure(Actor actor, Location location, int damage) {
    actor.hurt(damage);
    return actor + " fails to jump the " + location.getGround().toString() + ". Took " + damage + " fall damage. ";
  }

  /**
   * Generic code block to turn the jumpable object into Dirt, move the actor onto it and spawn a coin in its place
   *
   * @param actor    the actor jumping
   * @param location the location of the jumpable object
   * @return Generic message for destroying a jumpable object
   */
  default String destroy(Actor actor, Location location) {
    String destroyed = location.getGround().toString();
    location.map().moveActor(actor, location);
    location.setGround(new Dirt());
    location.addItem(new Coin(5));

    return actor + " destroys the " + destroyed + "! ";
  }

  /**
   * @return the success rate for jumping onto the jumpable object
   */
  int getJumpSuccessRate();

  /**
   * @return the fall damage that the actor will take if they fail the jump
   */
  int getFallDamage();

  /**
   * @return a string with flavour text for succeeding a jump
   */
  String getFlavourJump();

  /**
   * @return a string with flavour text for failing a jump
   */
  String getFlavourFail();

  /**
   * @return a string with flavour text for destroying a jumpable object
   */
  String getFlavourDestroy();
}

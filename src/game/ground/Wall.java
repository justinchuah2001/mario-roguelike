package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;

/**
 * The Wall class is a class that represents a wall. Standard, impassable terrain by normal means.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see edu.monash.fit2099.engine.positions.Ground
 */

public class Wall extends Ground implements Jumpable {
  int fallDamage;

  /**
   * A constructor for the Wall class
   */
  public Wall() {
    super('#');
    this.fallDamage = 20;
  }

  /**
   * Override of the toString method
   *
   * @return A string with the name of the Ground
   */
  public String toString() {
    return "Wall";
  }

  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    if (!location.containsAnActor()) {
      return new ActionList(new JumpAction(this, location, direction));
    }
    return new ActionList();
  }

  @Override
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(Status.FLYING);
  }

  @Override
  public boolean blocksThrownObjects() {
    return true;
  }

  @Override
  public int getJumpSuccessRate() {
    return 80;
  }

  @Override
  public int getFallDamage() {
    return 20;
  }

  @Override
  public String getFlavourJump() {
    return "Hup! ";
  }

  @Override
  public String getFlavourFail() {
    return "Bonk! ";
  }

  @Override
  public String getFlavourDestroy() {
    return "You're wrecking the place! ";
  }
}

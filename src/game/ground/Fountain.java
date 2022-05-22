package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.FillBottleAction;

/**
 * Magical ground that somehow provides special effects from its water
 *
 * @author Justin Chuah
 * @version 1.0
 */
public abstract class Fountain extends Ground {
  /**
   * Water available at the fountain
   */
  private int availableWater;
  /**
   * Turns needed for water to be refilled when depleted.
   */
  private int refillTimer;

  /**
   * Constructor.
   *
   * @param displayChar character to display for this type of terrain
   */
  public Fountain(char displayChar) {
    super(displayChar);
    this.availableWater = 10;
    this.refillTimer = 5;
    this.addCapability(Status.ON_FOUNTAIN);
  }

  /**
   * Determine what actions can be taken upon this class
   *
   * @param actor     the Actor acting
   * @param location  the current Location
   * @param direction the direction of the Ground from the Actor
   * @return Possible actions that can be acted upon this class.
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList actions = new ActionList();
    // If Player is on the fountain, has bottle and fountain is not depleted, allows player to fill bottle with water from fountain
    if (actor.hasCapability(Status.HAS_BOTTLE) && (location.containsAnActor()) && (!this.hasCapability(Status.IS_DEPLETED))) {
      actions.add(new FillBottleAction(location, this.availableWater));
    }
    return actions;
  }

  /**
   * Manages the condition of the fountain.
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    if (this.hasCapability(Status.IS_DEPLETED) && this.refillTimer > 0) { // If depleted, refill timer decrease
      this.refillTimer -= 1;
      if (this.refillTimer == 0) { //If timer is done, refresh the Fountain
        this.removeCapability(Status.IS_DEPLETED);
        refreshedFountain();
      }
    } else if (!this.hasCapability(Status.IS_DEPLETED)) { //If fountain is not depleted.
      if (this.hasCapability(Status.WAS_COLLECTED)) { //... then check if it was filled using bottle by the player
        this.availableWater -= 1; //Reduce available water.
        this.removeCapability(Status.WAS_COLLECTED);
      } else if (this.hasCapability(Status.DRANK_FROM)) { //... then check if it was drank by other actors
        this.availableWater -= 1; //Reduce available water
        this.removeCapability(Status.DRANK_FROM);
      }
      if (this.availableWater == 0) { //If no water remains, fountain is now depleted, refill timer begins.
        this.addCapability(Status.IS_DEPLETED);
      }
    }
  }

  /**
   * Returns the fountain to its original conditions.
   */
  public void refreshedFountain() {
    this.refillTimer = 5;
    this.availableWater = 10;
  }


}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Fire;

/**
 * This action allows Player to create fire on the targeted ground.
 * @author Justin Chuah, Caelan Kao
 * @version 1.0
 * @see game.items.FireFlower
 * @see game.items.Fire
 */
public class FireAttackAction extends Action {
  /**
   * Target of the action
   */
  private final Actor target;
  /**
   *  Direction of the target
   */
  private final String direction;

  /**
   * Constructor for this action
   * @param target  Target of the action
   * @param direction Direction of the target
   */
  public FireAttackAction(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  /**
   * This function let an Actor set fire to a specified ground
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return A description whether the actor successfully set fire to the targeted ground.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.hasCapability(Status.SHOOTING_FIRE)) { //If player under the power of Fire Flower, sets fire to the target's ground
      map.locationOf(target).addItem(new Fire());
      if (actor.hasCapability(Status.INVINCIBLE)){ //If player under effect of Power Star and Fire Flower, kills target after setting fire
        map.removeActor(target);
      }
      return actor + " sets fire on " + target;
    }
    return "Mario cant shoot fire! .....yet";
  }

  /**
   * This function returns the menu description of the action.
   * @param actor The actor performing the action.
   * @return Menu description of the action.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " set fire to " + target + " at " + direction;
  }
}

package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status.PermanentStatus;
import game.items.Bottle;

/**
 * This action allows Player to get Bottle from Toad.
 *
 * @author Jia Zheng
 * @version 1.0
 * @see game.actors.Toad
 * @see game.items.Bottle
 */
public class GetBottleFromToadAction extends Action {
  /**
   * This function lets the Player get the Bottle from Toad
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return A description of actor receiving the bottle from Toad
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    if (!actor.hasCapability(PermanentStatus.HAS_BOTTLE)) { //If Player does not have bottle, give him the bottle.
      actor.addItemToInventory(new Bottle());
    }
    return actor + " gets Bottle from Toad!";
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param actor The actor performing the action.
   * @return Menu description of the action.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " gets Bottle from Toad.";
  }
}

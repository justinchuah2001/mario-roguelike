package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * This action allows the Player to destroy a Koopa Shell(Dormant state koopa)
 *
 * @author Justin Chuah
 * @version 1.0
 * @see game.actors.Koopa
 */
public class BreakShellAction extends Action {
  /**
   * target of the action, in this case a Koopa
   */
  protected Actor target;
  /**
   * Direction of incoming action
   */
  protected String direction;

  /**
   * Constructor
   *
   * @param target    Koopa shell that is being targeted
   * @param direction
   */
  public BreakShellAction(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  /**
   * This function let an Actor destroy the koopa shell if he has a wrench
   *
   * @param actor The Actor who destroys the shell
   * @param map   The map the actor is on.
   * @return A description whether the actor successfully destroys the shell or not
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    // Check whether it is a Koopa shell
    if (target.hasCapability(Status.DORMANT)) {
      ActionList dropActions = new ActionList();
      // drop all items
      for (Item item : target.getInventory())
        dropActions.add(item.getDropAction(actor));
      for (Action drop : dropActions)
        drop.execute(target, map);
      // remove actor
      map.removeActor(target);
      return actor + " has destroyed " + target + "shell.";
    }

    return "Can only destroy Koopa shell!";
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param actor Actor who does the destroying of the Koopa shell
   * @return Menu description of the action.
   */

  @Override
  public String menuDescription(Actor actor) {
    return actor + " destroy shell at " + direction;
  }
}

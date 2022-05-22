package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * This action allows the player to end the game if he talks to the princess after picking up the key from bowser.
 *
 * @author Justin Chuah
 * @version 1.0
 * @see game.actors.PrincessPeach
 */
public class SavePrincessAction extends Action {
  /**
   * Target, in this case the princess.
   */
  protected Actor target;

  /**
   * Saves the princess and ends the game!
   *
   * @param target PrincessPeach
   */
  public SavePrincessAction(Actor target) {
    this.target = target;
  }

  /**
   * This function lets the Player saves the Princess
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return A text that signifies the game is over.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    map.removeActor(actor);
    return "You have successfully saved the princess, congratulations!!!!";
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param actor The actor performing the action.
   * @return Menu description of action
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " saves the princess?";
  }
}

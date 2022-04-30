package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * This action allows User to reset the game.
 * @author Chan Jia Zheng
 * @version 1
 * @see edu.monash.fit2099.engine.positions.World
 */
public class ResetAction extends Action {

  /**
   * This function execute the action.
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   * @return Message shows that the game is reset.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    ResetManager.getInstance().run();
    return String.format("%s Successfully reset the Game", actor);
  }

  /**
   * This function returns the menu description of the action.
   * @param actor The actor performing the action.
   * @return Menu description of the action.
   */
  @Override
  public String menuDescription(Actor actor) {
    return String.format("%s reset the Game", actor);
  }

  /**
   * This function returns the hot key of the action when it is shown in the menu.
   * @return hot key of the action
   */
  @Override
  public String hotkey(){
    return "r";
  }
}

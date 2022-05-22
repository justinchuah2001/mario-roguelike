package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status.PermanentStatus;
import game.Status.TempStatus;

import java.util.ArrayList;

/**
 * This action allows Player to talk to the Toad.
 *
 * @author Chan Jia Zheng
 * @version 1
 * @see game.actors.Toad#allowableActions(Actor, String, GameMap)
 */
public class TalkToToadAction extends Action {
  /**
   * The actor who wants to talk to Toad.
   */
  protected Actor target;

  /**
   * constructor
   *
   * @param target The actor who wants to talk to Toad.
   */
  public TalkToToadAction(Actor target) {
    this.target = target;
  }

  /**
   * This action returns the monologue of Player with the Toad based on the Player's Status.
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return The monologue of Player interact with the Toad.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    ArrayList<String> monologues = new ArrayList<>();
    monologues.add("The Princess is depending on you! You are our only hope.");
    monologues.add("Being imprisoned in these walls can drive a fungus crazy :(");
    if (!actor.hasCapability(PermanentStatus.HAS_WRENCH)) {
      monologues.add("You might need a wrench to smash Koopa's hard shells.");
    }
    if (!actor.hasCapability(TempStatus.TALL)) {
      monologues.add("You better get back to finding the Power Stars.");
    }

    return monologues.get((int) (Math.random() * monologues.size()));
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param actor The actor performing the action.
   * @return Menu description of the action.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " talks to Toad";
  }
}

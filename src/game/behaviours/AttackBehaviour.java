package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * A class that allows an AttackAction for the actor if it is possible
 *
 * @author Justin Chuah
 * @version 1.0
 */
public class AttackBehaviour implements Behaviour {
  /**
   * Target that is being attacked
   */
  private Actor target;
  /**
   * The direction of incoming attack.
   */
  private String direction;

  /**
   * Constructor
   *
   * @param target    Actor to attack
   * @param direction The direction of incoming attack.
   */
  public AttackBehaviour(Actor target, String direction) {
    this.target = target;
    this.direction = direction;

  }

  /**
   * Returns an attack action to the actor if there are actors hostile to the actor on its surrounding tiles,
   * else return null
   *
   * @param actor the Actor acting
   * @param map   the GameMap containing the Actor
   * @return an Action, or null if no hostile actors are detected
   */
  @Override
  public Action getAction(Actor actor, GameMap map) {
    if (!map.contains(target) || !map.contains(actor))
      return null;

    Location here = map.locationOf(actor);
    Location there = map.locationOf(target);

    for (Exit exit : here.getExits()) {
      Location destination = exit.getDestination();
      if (destination == there) {
        return new AttackAction(target, direction);
      }
    }

    return null;
  }
}

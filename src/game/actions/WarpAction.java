package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Warpable;

/**
 * The warp action allows a warper to warp to a different gamemap
 *
 * @author Caelan Kao
 * @version 1.1
 * @see game.ground.WarpPipe
 * @see game.actors.Player
 */
public class WarpAction extends Action {
  private Warpable warpable;
  private Location source;
  private GameMap sourceMap;
  private Location destination;
  private String destinationName;

  /**
   * Constructor for the warp action
   *
   * @param warper          the actor warping
   * @param source          the starting location of the warping actor
   * @param sourceMap       the starting map of the warping actor
   * @param destination     the destination that the actor is warping to
   * @param destinationName the name of the map the actor is warping to
   */
  public WarpAction(Warpable warper, Location source, GameMap sourceMap,
                    Location destination, String destinationName) {
    this.warpable = warper;
    this.source = source;
    this.sourceMap = sourceMap;
    this.destination = destination;
    this.destinationName = destinationName;
  }

  /**
   * Calls the warp method to warp the actor
   *
   * @param actor The actor performing the action.
   * @param map   The map the actor is on.
   * @return A string that describes where the actor has been warped to, and to which world
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    return this.warpable.warp(actor, this.source, this.sourceMap, this.destination, this.destinationName);
  }

  /**
   * Returns the menu description of the action.
   *
   * @param actor The actor performing the action.
   * @return menu description of the action
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " warps to " + this.destinationName;
  }
}

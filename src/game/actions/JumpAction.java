package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.ground.Jumpable;

/**
 * A class that represents a Jump action, that allows an actor to jump onto a jumpable object
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see edu.monash.fit2099.engine.actions.Action
 * @see game.ground.Jumpable
 */
public class JumpAction extends Action {
  private Jumpable jumpable;
  private Location jumpableLocation;
  private String direction;


  /**
   * Constructor for the JumpAction class
   *
   * @param jumpable         the jumpable object
   * @param jumpableLocation the location of the jumpable object
   * @param direction        the direction of the jumpable object relative to the actor
   */
  public JumpAction(Jumpable jumpable, Location jumpableLocation, String direction) {
    this.jumpable = jumpable;
    this.jumpableLocation = jumpableLocation;
    this.direction = direction;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    return this.jumpable.jump(actor, this.jumpableLocation);
  }

  @Override
  public String menuDescription(Actor actor) {
    if (actor.hasCapability(Status.INVINCIBLE)) {
      return actor + " runs through the " + this.jumpable.toString() + "(" + this.jumpableLocation.x() + ", "
              + this.jumpableLocation.y() + ") to the " + this.direction;
    }
    return actor + " jumps onto the " + this.jumpable.toString() + "(" + this.jumpableLocation.x() + ", "
            + this.jumpableLocation.y() + ") to the " + this.direction;
  }
}

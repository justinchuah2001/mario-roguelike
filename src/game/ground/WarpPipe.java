package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.WarpAction;
import game.actors.PiranhaPlant;
import game.actors.Warpable;

/**
 * A Warp Pipe, used to travel between areas, home to Piranha Plants.
 *
 * @author Caelan Kao
 * @version 1.1
 */
public class WarpPipe extends Ground implements Jumpable {
  private int turnsActive = 0;
  private boolean piranhaPlantSpawned = false;

  /**
   * A constructor of the Warp Pipe class.
   */
  public WarpPipe() {
    super('C');

  }

  /**
   * Overriding the toString class
   *
   * @return name of the Ground
   */
  public String toString() {
    return "Warp Pipe";
  }

  /**
   * Spawns a Piranha Plant on turn 2, and immediately whenever the game is reset
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    this.turnsActive++;
    if (this.turnsActive >= 1 && !this.piranhaPlantSpawned && !location.containsAnActor()) {
      location.addActor(new PiranhaPlant());
      this.piranhaPlantSpawned = true; //on reset, reset the boolean to false so plants can respawn
    }
  }

  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    if (!location.containsAnActor()) {
      return new ActionList(new JumpAction(this, location, direction));

    } else if (location.getActor() instanceof Warpable) {
      Warpable warpableActor = (Warpable) actor;
      for (String mapName : warpableActor.getWorldList().keySet())
        if (!warpableActor.getWorldList().get(mapName).contains(actor)) {

          GameMap map = warpableActor.getWorldList().get(mapName);

          return new ActionList(new WarpAction(warpableActor, location, location.map(),
                  warpableActor.getPreviousWarpPoint().get(map), mapName));
        }
    }
    return new ActionList();
  }


  @Override
  public boolean canActorEnter(Actor actor) {
    return false;
  }

  @Override
  public int getJumpSuccessRate() {
    return 100;
  }

  @Override
  public int getFallDamage() {
    return 0;
  }

  @Override
  public String getFlavourJump() {
    return "Off to another world? ";
  }

  @Override
  public String getFlavourFail() {
    return null;
  }

  @Override
  public String getFlavourDestroy() {
    return "Careful not to destroy all of them or it's game over man! ";
  }
}

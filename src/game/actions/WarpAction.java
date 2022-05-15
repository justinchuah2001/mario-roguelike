package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.ground.Warpable;

public class WarpAction extends Action {
  private Warpable warpable;
  private Location warpDestination;
  private String direction;

  public WarpAction(Warpable warpable, Location warpDestination, String direction) {
    this.warpable = warpable;
    this.warpDestination = warpDestination;
    this.direction = direction;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    return warpable.warp(actor, warpDestination);
  }

  @Override
  public String menuDescription(Actor actor) {
    //((Player) actor).getWorldList()

    return actor + " warps to ";
  }
}

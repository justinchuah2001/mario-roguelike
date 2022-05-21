package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;

public class GetBottleFromToadAction extends Action {
  @Override
  public String execute(Actor actor, GameMap map) {
    actor.addCapability(Status.HAS_BOTTLE);
    actor.addItemToInventory(new Bottle());
    return actor + " gets Bottle from Toad!";
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " gets Bottle from Toad.";
  }
}

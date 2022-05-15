package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Fire;

public class FireAttack extends Action {
  private Actor target;
  private String direction;

  public FireAttack(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.hasCapability(Status.FIRE)) {
      map.locationOf(target).addItem(new Fire());
      return actor + " set fire at " + target;
    }
    return null;
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " set fire to " + target + " at " + direction;
  }
}

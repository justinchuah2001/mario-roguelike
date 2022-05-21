package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Fire;

public class FireAttackAction extends Action {
  private Actor target;
  private String direction;

  public FireAttackAction(Actor target, String direction) {
    this.target = target;
    this.direction = direction;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    if (actor.hasCapability(Status.SHOOTING_FIRE)) {
      map.locationOf(this.target).addItem(new Fire());
      if (actor.hasCapability(Status.INVINCIBLE)){
        map.removeActor(this.target);
      }
      return actor + " sets fire on " + this.target;
    }
    return "Mario cant shoot fire! .....yet";
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " set fire to " + this.target + " at " + this.direction;
  }
}

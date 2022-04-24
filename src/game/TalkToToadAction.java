package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class TalkToToadAction extends Action {
  protected Actor target;

  public TalkToToadAction(Actor target) {
    this.target = target;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    return menuDescription(this.target);
  }

  @Override
  public String menuDescription(Actor actor) {
    return null;
  }
}

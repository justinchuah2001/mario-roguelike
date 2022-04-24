package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ResetAction extends Action {
  private ResetManager resetManager = ResetManager.getInstance();

  @Override
  public String execute(Actor actor, GameMap map) {
    this.resetManager.run();
    return menuDescription(actor);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " reset the game. ";
  }
}

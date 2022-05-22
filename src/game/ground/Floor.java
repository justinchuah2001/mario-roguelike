package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status.PermanentStatus;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
  /**
   * A constructor for the Floor class
   */
  public Floor() {
    super('_');
  }

  @Override
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(PermanentStatus.HOSTILE_TO_ENEMY);
  }
}

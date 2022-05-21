package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.items.HealingWater;

public class HealthFountain extends Fountain {
  /**
   * Constructor.
   *

   */
  public HealthFountain() {
    super('H');
    this.addCapability(Status.HEALING);
  }

}

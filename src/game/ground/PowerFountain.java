package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.items.PowerWater;

public class PowerFountain extends Fountain {
  /**
   * Constructor.

   */
  public PowerFountain() {
    super('A');
    this.addCapability(Status.POWERING);
  }

}

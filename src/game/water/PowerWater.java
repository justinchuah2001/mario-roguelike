package game.water;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * Water that can be found at the Power Fountain, its makes the consumer stronger!
 *
 * @author Justin Chuah
 * @version 1.0
 */
public class PowerWater extends Water {
  /**
   * Constructor
   */
  public PowerWater() {
    super("Power Water");
  }

  /**
   * Effects that is taken upon the consumer
   *
   * @param actor actor who consumes the water
   */
  @Override
  public void waterEffect(Actor actor) {
    actor.addCapability(Status.POWER_UP);
  }
}

package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status.PermanentStatus;
import game.Status.TempStatus;
import game.actors.Player;

/**
 * A fire flower, makes you shoot fire! Hooray arson!
 *
 * @author Caelan Kao
 * @version 1.1
 * @see game.ground.Tree
 */
public class FireFlower extends Consumables {
  /***
   * A constructor for the fire flower
   */
  public FireFlower() {
    super("Fire Flower", 'f', true);
  }

  /**
   * Grants the actor consuming the fire flower the SHOOTING_FIRE status
   *
   * @param actor Actor consuming
   */
  @Override
  public void consumedEffect(Actor actor) {
    actor.addCapability(TempStatus.SHOOTING_FIRE);
    ((Player) actor).addTimedStatus(TempStatus.SHOOTING_FIRE, 20);
  }
}

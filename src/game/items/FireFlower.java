package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.actors.Player;

public class FireFlower extends Consumables {
  /***
   * A constructor for the fire flower
   */
  public FireFlower() {
    super("Fire Flower", 'f', true);
  }

  @Override
  public void consumedEffect(Actor actor) {
    actor.addCapability(Status.SHOOTING_FIRE);
    ((Player) actor).addTimedStatus(Status.SHOOTING_FIRE,20);
  }
}

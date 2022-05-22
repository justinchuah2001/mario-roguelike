package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status.PermanentStatus;

/**
 * Burning hot lava instead of solid ground, best not to touch it, will probably hurt. A lot.
 *
 * @author Caelan Kao
 * @version 1.1
 */
public class Lava extends Ground {
  /**
   * A Constructor for the lava class.
   */
  public Lava() {
    super('L');
  }

  /**
   * Burns any actor on this Lava every turn
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    if (location.containsAnActor()) {
      location.getActor().hurt(15);
      System.out.println(location.getActor() + " burns for 15 damage");
      if (!location.getActor().isConscious()) {
        location.map().removeActor(location.getActor());
      }
    }
  }

  /**
   * only allow actors with HOSTILE_TO_ENEMY to walk
   *
   * @param actor the Actor to check
   * @return boolean if the actor can walk on this ground
   */
  @Override
  public boolean canActorEnter(Actor actor) {
    return actor.hasCapability(PermanentStatus.HOSTILE_TO_ENEMY);
  }
}

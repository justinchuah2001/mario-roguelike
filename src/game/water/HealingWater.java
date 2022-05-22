package game.water;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Healing Water found at the  Healing Fountain
 *
 * @author Justin Chuah
 * @version 1.0
 */
public class HealingWater extends Water {
  /**
   * Constructor
   */
  public HealingWater() {
    super("Healing Water");
  }

  /**
   * Effects of the water on the actor
   *
   * @param actor actor who receives the effects
   */
  @Override
  public void waterEffect(Actor actor) {
    actor.heal(50);
  }
}

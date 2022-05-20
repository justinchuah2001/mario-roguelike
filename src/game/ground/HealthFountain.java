package game.ground;

import edu.monash.fit2099.engine.actors.Actor;

public class HealthFountain extends Fountain {
  /**
   * Constructor.
   *
   * @param displayChar character to display for this type of terrain
   */
  public HealthFountain(char displayChar) {
    super('H');
  }

  @Override
  public void drinkEffects(Actor actor) {
    actor.heal(50);
  }
}

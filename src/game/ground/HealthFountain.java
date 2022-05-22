package game.ground;


import game.Status;

/**
 * A fountain that heals the actor who drinks from it
 */
public class HealthFountain extends Fountain {
  /**
   * Constructor.
   */
  public HealthFountain() {
    super('H');
    this.addCapability(Status.HEALING);
  }

}

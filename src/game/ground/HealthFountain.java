package game.ground;


import game.Status.PermanentStatus;

/**
 * A fountain that heals the actor who drinks from it
 */
public class HealthFountain extends Fountain {
  /**
   * Constructor.
   */
  public HealthFountain() {
    super('H');
    this.addCapability(PermanentStatus.HEALING);
  }

}

package game.ground;


import game.Status.PermanentStatus;

/**
 * A fountain that increases the attack of actor who drinks from it.
 */
public class PowerFountain extends Fountain {
  /**
   * Constructor.
   */
  public PowerFountain() {
    super('A');
    this.addCapability(PermanentStatus.POWERING);
  }

}

package game.ground;


import game.Status;


public class PowerFountain extends Fountain {
  /**
   * Constructor.

   */
  public PowerFountain() {
    super('A');
    this.addCapability(Status.POWERING);
  }

}

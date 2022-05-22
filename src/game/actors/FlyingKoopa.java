package game.actors;


import game.Status.PermanentStatus;

/**
 * The same old Koopa.... except it flies now!
 *
 * @author Justin Chuah, Jia Zheng
 * @version 1.0
 */
public class FlyingKoopa extends Koopa {
  /**
   * Constructor.
   * Add behaviour that allows the actor to move around the map as a possible choice of action
   */
  public FlyingKoopa() {
    super("Flying Koopa", 'F', 150);
    this.addCapability(PermanentStatus.FLYING);
    this.monologue.addSentence("Pam pam pam!");
  }
}

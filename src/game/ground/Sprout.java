package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

/**
 * A class that represents a Sprout, the first stage of a Tree.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Tree
 * @see edu.monash.fit2099.engine.positions.Ground
 */
public class Sprout extends Tree {
  /**
   * A constructor for the Sprout class.
   * The age is initialised as 0
   */
  public Sprout() {
    super('+');
    this.setAge(0);
  }

  /**
   * Override of the toString method
   *
   * @return A string with the name of the Ground
   */
  public String toString() {
    return "Sprout";
  }

  @Override
  public String jump(Actor actor, Location location) {
    return super.jump(actor, location);
  }

  /**
   * Every turn, there is a 10% chance for a Goomba to spawn when there is no actor on this Sprout
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    if (r.nextInt(100) < 10 && !location.containsAnActor()) {
      location.addActor(new Goomba());
    }

    super.tick(location);
  }

  @Override
  public int getJumpSuccessRate() {
    return 90;
  }

  @Override
  public int getFallDamage() {
    return 10;
  }

  @Override
  public String getFlavourJump() {
    return "Boing~ ";
  }

  @Override
  public String getFlavourFail() {
    return "Oof! ";
  }

  @Override
  public String getFlavourDestroy() {
    return "Bye bye Goomba spawner~ ";
  }
}

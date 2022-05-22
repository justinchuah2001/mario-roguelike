package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status.PermanentStatus;
import game.water.Water;

import java.util.ArrayList;

/**
 * Bottle that is obtained through interaction with Toad.
 *
 * @author Justin Chuah
 * @version 1.0
 * @see game.actors.Toad
 */
public class Bottle extends Item {
  /**
   * Storage system used by the bottle.
   */
  private static final ArrayList<Water> bottleContent = new ArrayList<>();
  /**
   * Bottle instance
   */
  private static Bottle instance;

  /**
   * Constructor.
   * Adds the capability of showing that the actor(Player) currently holds the bottle.
   */
  private Bottle() {
    super("Bottle", 'b', false);
    this.addCapability(PermanentStatus.HAS_BOTTLE);
  }

  /**
   * Method of adding water into the bottle.
   *
   * @param water Type of water added
   */
  public void fillBottle(Water water) {
    bottleContent.add(water);
  }

  /**
   * Gets the entire array list of water stored in the bottle
   *
   * @return array list of water in the bottle
   */
  public ArrayList<Water> getBottleContent() {
    return bottleContent;

  }

  /**
   * Creates a bottle if bottle doesn't exist. Otherwise, retrieves the current instance of bottle
   *
   * @return instance of bottle
   */
  public static Bottle getInstance() {
    if (instance == null) {
      instance = new Bottle();
    }
    return instance;
  }

  /**
   * Prints the content of the bottle.
   *
   * @return Content of bottle
   */
  @Override
  public String toString() {
    return "Bottle " + this.getBottleContent();
  }
}

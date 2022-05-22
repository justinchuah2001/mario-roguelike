package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * An item that is needed to save the princess. Get it from Bowser!
 *
 * @author Justin Chuah
 * @version 1.0
 */
public class Key extends Item {
  /**
   * Constructor
   */
  public Key() {
    super("Key", 'k', true);
    this.addCapability(Status.HAS_KEY);
  }
}

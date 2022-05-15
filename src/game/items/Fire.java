package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Item {
  private int duration = 3;

  /***
   * Constructor.
   */
  public Fire() {
    super("Fire", 'v', false);
  }

  @Override
  public void tick(Location currentLocation) {
    if (currentLocation.containsAnActor()) {
      currentLocation.getActor().hurt(20);
      System.out.println(currentLocation.getActor() + " burns for 20 damage");
      if (!currentLocation.getActor().isConscious()) {
        currentLocation.map().removeActor(currentLocation.getActor());
      }
    }
    duration -= 1;
    if (duration == 0) {
      currentLocation.removeItem(this);
    }
  }
}

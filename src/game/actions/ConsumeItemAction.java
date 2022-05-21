package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumables;


/**
 * This action allows Player to consume any item that is consumable
 *
 * @author Justin Chuah
 * @version 1.0
 * @see game.items.Consumables
 */
public class ConsumeItemAction extends Action {
  /**
   * Item that is currently being consumed by player
   */
  protected Consumables consumedItem;

  /**
   * Action that is to be added to the action list if a consumable item is available
   *
   * @param consumedItem A consumable item
   */
  public ConsumeItemAction(Consumables consumedItem) {
    this.consumedItem = consumedItem;
  }

  /**
   * This function let an Actor consume a consumable item
   *
   * @param self The Actor who consumes the item
   * @param map  The map the actor is on.
   * @return A description whether the actor successfully consumes the item
   */
  @Override
  public String execute(Actor self, GameMap map) {

    for (Item item : self.getInventory())
      if (consumedItem == item) { // If item exists in inventory, consume it and gain its effects
        this.consumedItem.consumedEffect(self);
        self.removeItemFromInventory(item); // Remove item after consumption

        return self + " has consumed " + consumedItem;
      }
    // If item is on the ground, consume it directly and remove it from ground
    this.consumedItem.consumedEffect(self);
    map.locationOf(self).removeItem(consumedItem);
    return self + " has consumed " + consumedItem;
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param actor Actor who consumes the item
   * @return Menu description of the action.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " consume " + consumedItem;
  }

}

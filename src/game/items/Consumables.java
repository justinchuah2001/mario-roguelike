package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeItemAction;

/**
 * An abstract class for items that are consumable by the Player/ an actor
 *
 * @author Justin Chuah
 * @version 1.0
 */
public abstract class Consumables extends Item {
  /**
   * Constructor.
   * Adds the action that allows the actor to consume the consumable
   *
   * @param name        Name of the consumable item
   * @param displayChar Display character of the item on the map
   * @param portable    Boolean to determine whether item is droppable and pickable
   */
  public Consumables(String name, char displayChar, boolean portable) {
    super(name, displayChar, portable);
    this.addAction(new ConsumeItemAction(this));
  }

  /**
   * Set the actor under the effects of the consumed item
   *
   * @param actor Actor consuming
   */
  public abstract void consumedEffect(Actor actor);
}

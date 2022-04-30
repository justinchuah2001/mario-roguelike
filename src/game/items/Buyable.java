package game.items;

/**
 * Interface for Item in the game to show that item is buyable so it can be used
 * by BuyItemAction.
 *
 * @author Chan Jia Zheng
 * @version 1
 * @see game.items.Wrench
 * @see SuperMushroom
 * @see PowerStar
 */

/**
 * All Buyable item should have a price.
 */
public interface Buyable {
  int getPrice();
}

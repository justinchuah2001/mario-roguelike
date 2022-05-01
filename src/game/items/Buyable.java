package game.items;

/**
 * Interface for Item in the game to show that item is buyable so it can be used
 * by BuyItemAction. All Buyable item should have a price.
 *
 * @author Chan Jia Zheng
 * @version 1.0
 * @see game.items.Wrench
 * @see game.items.SuperMushroom
 * @see game.items.PowerStar
 */
public interface Buyable {
  /**
   * This function returns the price of the Buyable item.
   * @return Price of the Buyable item.
   */
  int getPrice();
}

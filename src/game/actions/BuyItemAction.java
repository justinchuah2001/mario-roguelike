package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Buyable;

import java.util.List;

/**
 * This action allows Player buy Item from other actors (Toad in this game).
 * @author Chan Jia Zheng
 * @version 1
 * @see game.actors.Toad#allowableActions(Actor, String, GameMap)
 */
public class BuyItemAction extends Action {
  /**
   * Seller of the item
   */
  private Actor seller;

  /**
   * The item to be traded.
   */
  private Item item;

  /**
   * Price of the item.
   */
  private int price;

  /**
   * Constructor. It is set to private as we should only meet the condition stated in
   * getInstance(Actor seller, Item item).
   *
   * @param seller Seller of the item.
   * @param item The item to be traded.
   */
  private BuyItemAction(Actor seller, Item item) {
    this.seller = seller;
    this.item = item;
    this.price = ((Buyable) item).getPrice();
  }

  /**
   * This function only creates the BuyItemAction for other modules if and only if the item is Buyable.
   *
   * @param seller Seller of the item.
   * @param item The item to be traded.
   * @return BuyItemAction
   */
  public static BuyItemAction getInstance(Actor seller, Item item){
    if (item instanceof Buyable){
      return new BuyItemAction(seller, item);
    } else {
      return null;
    }
  }

  /**
   * This function let an Actor buy the item from seller.
   *
   * @param buyer The Actor who wants to buy the item.
   * @param map The map the actor is on.
   * @return A description whether the actor successfully buys the item from seller.
   */
  @Override
  public String execute(Actor buyer, GameMap map) {
    Player player = (Player) buyer;
    String res;
    boolean sold = false;

    if (player.deductFromWallet(this.price)) {
      player.addItemToInventory(item);
      seller.removeItemFromInventory(item);
      sold = true;
    }

    if (sold) {
      res = buyer + " buys " + item + " from " + seller + ".";
    } else {
      res = "insufficient coin for " + buyer + " to buy " + item + " from " + seller + ".";
    }
    return res;
  }

  /**
   * This function returns the menu description of the action.
   *
   * @param buyer Actor who wants to buy the item.
   * @return Menu description of the action.
   */
  @Override
  public String menuDescription(Actor buyer) {
    return String.format("%s buys Power Star ($%d)", buyer, this.price);
  }
}

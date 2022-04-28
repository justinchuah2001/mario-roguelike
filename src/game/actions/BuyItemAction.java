package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Buyable;

import java.util.List;

public class BuyItemAction extends Action {
  private Actor seller;
  private Item item;
  private int price;

  public BuyItemAction(Actor seller, Item item) {
    this.seller = seller;
    this.item = item;
    this.price = ((Buyable) item).getPrice();
  }

  @Override
  public String execute(Actor buyer, GameMap map) {
    Player player = (Player) buyer;
    String res;
    boolean sold = false;

    if (player.deductFromWallet(this.price)) {
      player.addItemToInventory(item);
      seller.removeItemFromInventory(item);
    }

    if (sold) {
      res = buyer + " buys " + item + " from " + seller;
    } else {
      res = "insufficient coin for " + buyer + " to buy " + item + " from " + seller;
    }
    return res;
  }

  @Override
  public String menuDescription(Actor buyer) {
    return String.format("%s buys Power Star ($%d)", buyer, this.price);
  }
}

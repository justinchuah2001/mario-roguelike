package game;

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
  private boolean sold;

  public BuyItemAction(Actor seller, Item item) {
    this.seller = seller;
    this.item = item;
    this.sold = false;
  }

  @Override
  public String execute(Actor buyer, GameMap map) {
    Player player = (Player) buyer;
    Buyable commodity = (Buyable) this.item;

    if (player.deductFromWallet(commodity.getPrice())) {
      player.addItemToInventory(item);
      seller.removeItemFromInventory(item);
    }
    return this.menuDescription(buyer);
  }

  @Override
  public String menuDescription(Actor actor) {
    String res;
    if (this.sold) {
      res = actor + " buys " + item + " from " + seller;
    } else {
      res = "in sufficient coin for " + actor + " to buy " + item + " from " + seller;
    }
    return res;
  }
}

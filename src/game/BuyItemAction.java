package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

public class BuyItemAction extends Action {
  private Actor seller;
  private Item item;

  public BuyItemAction(Actor seller, Item item) {
    this.seller = seller;
    this.item = item;
  }

  @Override
  public String execute(Actor buyer, GameMap map) {
    buyer.addItemToInventory(item);
    seller.removeItemFromInventory(item);
    return this.menuDescription(buyer);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " buys " + item + " from " + seller;
  }
}

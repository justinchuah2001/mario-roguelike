package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;

/**
 * This action allows an actor to pick up a Coin object, add the value to the actor's wallet, and instead of adding
 * the coin into the inventory, remove it from the map.
 *
 * @author Justin Chuah
 * @version 1.0
 * @see game.items.Coin
 */
public class PickUpCoinAction extends Action {
  private Item item;
  private Coin coin;

  public PickUpCoinAction(Item item) {
    this.item = item;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    map.locationOf(actor).removeItem(item);
    coin = (Coin) item;
    Player player = (Player) actor;
    player.addToWallet(coin.getCoinValue());

    return menuDescription(actor);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " picks up the " + item;
  }
}

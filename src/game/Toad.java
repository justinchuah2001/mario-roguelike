package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.TalkToToadAction;
import game.items.Buyable;
import game.items.PowerStar;
import game.items.SuperMushroom;

public class Toad extends Actor {
  /**
   * Constructor.
   *
   */
  public Toad() {
    super("Toad", 'T', 1);
    this.addItemToInventory(new SuperMushroom());
    this.addItemToInventory(new PowerStar());
    this.addItemToInventory(new Wrench());
  }

  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
    if(otherActor.hasCapability(Status.TALK_TO_TOAD)) {
      actions.add(new TalkToToadAction(otherActor));
    } else if (otherActor.hasCapability(Status.BUY_FROM_TOAD)){
      for (Item item : this.getInventory()){
        Action buyAction = BuyItemAction.getInstance(this, item);
        if (buyAction != null){
          actions.add(buyAction);
        }
      }
    }
    return actions;
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}

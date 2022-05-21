package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;
import game.items.Key;
import game.reset.Resettable;

public class Bowser extends Enemy implements Resettable {
  /**
   * Sentences said by Bowser.
   */
  private final static String[] sentences = {"What was that sound? Oh, just a fire.",
          "Princess Peach! You are formally invited... to the creation of my new kingdom!",
          "Never gonna let you down!",
          "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!"};


  /**
   * Constructor for Bowser
   * Add behaviour that allows the actor to move around the map as a possible choice of action
   */
  public Bowser() {
    super("Bowser", 'B', 500);
    this.monologue = new Monologue(this, sentences);
    this.behaviours.remove(10);
    this.addItemToInventory(new Key());
    this.registerInstance();
  }

  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(80, "punches");
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    if(this.hasCapability(Status.RESET)){
      this.increaseMaxHp(0);
    }
    this.monologue.show(display);
    for (game.behaviours.Behaviour Behaviour : behaviours.values()) {
      Action action = Behaviour.getAction(this, map);
      if (action != null)
        return action;
    }
    return new DoNothingAction();
  }

  @Override
  public void resetInstance() {
    this.addCapability(Status.RESET);
  }
}

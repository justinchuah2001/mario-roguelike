package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.items.Key;

public class Bowser extends Enemy {
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
    behaviours.remove(10);
    this.addItemToInventory(new Key());
    this.monologue = new Monologue(this, sentences);
  }

  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(80, "punches");
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.monologue.show(display);
    return new DoNothingAction();
  }
}

package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Monologue;
import game.Status;
import game.items.Key;
import game.reset.Resettable;

/**
 * The final boss of the game. Defeat him to save the Princess!
 *
 * @author Justin Chuah, Jia Zheng
 * @version 1.0
 */
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
    this.behaviours.remove(10); // Removes the capability of wandering
    this.addItemToInventory(new Key()); // Key that is required to end the game
    this.registerInstance();
    this.addCapability(Status.FINAL_BOSS);
  }

  /**
   * Weapon used by Bowser, since he is already so strong he can't be buffed!
   *
   * @return Weapon of choice of Bowser.
   */
  @Override
  protected IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(80, "punches");
  }

  /**
   * Figure out what to do next
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return Action according to behaviour or does nothing!
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    this.monologue.show(display);
    if (this.hasCapability(Status.RESET)) {
      this.increaseMaxHp(0);
    }
    for (game.behaviours.Behaviour Behaviour : this.behaviours.values()) {
      Action action = Behaviour.getAction(this, map);
      if (action != null)
        return action;
    }
    return new DoNothingAction();
  }

  /**
   * Notifies the game to reset this char.
   */
  @Override
  public void resetInstance() {
    this.addCapability(Status.RESET);
  }
}

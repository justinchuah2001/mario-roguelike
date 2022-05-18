package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.reset.Resettable;
import game.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class representing the Player.
 *
 * @author Justin Chuah, Chan Jia Zheng
 * @version 1.0
 */
public class Player extends Actor implements Resettable {
  /**
   * List of timed statuses that are active on the player
   */
  private ConcurrentHashMap<Status, Integer> timedStatusHashMap = new ConcurrentHashMap<>();

  /**
   * Menu
   */
  private final Menu menu = new Menu();
  /**
   * Wallet to store track how many coins the player has
   */
  private int wallet = 0;

  private ArrayList<GameMap> worldList;


  /**
   * Constructor.
   *
   * @param name        Name to call the player in the UI
   * @param displayChar Character to represent the player in the UI
   * @param hitPoints   Player's starting number of hitpoints
   */
  public Player(String name, char displayChar, int hitPoints, ArrayList<GameMap> worldList) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.addCapability(Status.BUY_FROM_TOAD);
    this.addCapability(Status.TALK_TO_TOAD);
    this.registerInstance();
    this.worldList = worldList;
  }


  /**
   * Select and return an action to perform on the current turn.
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return the Action to be performed
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    // Handle multi-turn Actions
    if (lastAction.getNextAction() != null)
      return lastAction.getNextAction();

    // Turn counter for the statuses effect on player
    countdownStatus(display);

    // return/print the console menu
    display.println("Mario " + this.printHp() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")");
    display.println("Wallet: " + this.wallet);
    display.println("");
    return menu.showMenu(this, actions, display);
  }

  /**
   * Gets current display character
   *
   * @return Changes current display character from lower-case to upper-case if Player has TALL status.
   */
  @Override
  public char getDisplayChar() {
    return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
  }

  /**
   * This function reset the Player when the user choose to reset the game.
   */
  @Override
  public void resetInstance() {
    this.heal(this.getMaxHp());
    this.removeCapability(Status.TALL);
    this.removeCapability(Status.INVINCIBLE);
    this.removeCapability(Status.SHOOTING_FIRE);
  }

  /**
   * This function add coins to the Player's wallet.
   *
   * @param coinValue The value of coin that we want to add to the Player's wallet.
   */
  public void addToWallet(int coinValue) {
    if (coinValue > 0) {
      this.wallet += coinValue;
    }
  }

  /**
   * This
   *
   * @param amount How much we want to deduct from Player's wallet.
   * @return Whether the amount is successfully deducted from Player's wallet.
   * @see game.actions.BuyItemAction
   */
  public boolean deductFromWallet(int amount) {
    boolean valid = false;
    if (amount > 0 && this.wallet >= amount) {
      this.wallet -= amount;
      valid = true;
    }
    return valid;
  }

  /**
   * Counts down the duration for any active statuses, calls removeStatus when duration hits 0
   * and displayStatus any other time
   *
   * @param display the display for the game
   */
  public void countdownStatus(Display display){
    for (Status i: timedStatusHashMap.keySet()) {
      int temp = timedStatusHashMap.get(i);
      temp -= 1;
      if (temp == 0){
        display.println( removeStatus(i) );
        timedStatusHashMap.remove(i);

      }else{
        timedStatusHashMap.replace(i, timedStatusHashMap.get(i), temp);
        display.println(displayStatus(i));
      }
    }
  }

  /**
   * Returns a string that displays the remaining duration for a timed status
   *
   * @param status The active status
   * @return a string that displays the remaining duration for a timed status
   */
  public String displayStatus(Status status){
    return "Mario is " + status.name() + "! - " + timedStatusHashMap.get(status) + " turns remain";
  }

  /**
   * Remove status from the player when duration hits 0
   * @param status The status being removed
   * @return A string that informs the user that the status has worn off
   */
  public String removeStatus(Status status){
    this.removeCapability(status);
    return this.name + " is no longer " + status.name().toLowerCase();
  }

  /**
   * Method for classes for Statuses with timers to call so that they can be tracked
   * @param status  the status being added
   * @param timer   the duration of the status
   */
  public void addTimedStatus(Status status, int timer){
    timedStatusHashMap.put(status, timer);
    
  }

  public ArrayList<GameMap> getWorldList() {
    return worldList;
  }
}
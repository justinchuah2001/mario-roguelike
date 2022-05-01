package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.reset.Resettable;
import game.Status;

/**
 * Class representing the Player.
 *
 * @author Justin Chuah, Chan Jia Zheng
 * @version 1.0
 */
public class Player extends Actor implements Resettable {
	/**
	 * Counter to track power star effect (Last for 10 turns)
	 */
	private int counter = 10;
	/**
	 * Menu
	 */
	private final Menu menu = new Menu();
	/**
	 * Wallet to store track how many coins the player has
	 */
	private int wallet = 0;


	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.BUY_FROM_TOAD);
		this.addCapability(Status.TALK_TO_TOAD);
		this.registerInstance();
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

		// Turn counter for the power star effect on player
		if (this.hasCapability(Status.INVINCIBLE)) {
			final int WEAR_OFF = 0; //Constant value to determine the turn where power star effect wears off
			if (counter == WEAR_OFF) {
				this.removeCapability(Status.INVINCIBLE);
				display.println("Mario is no longer invincible.");
			} else {
				display.println("Mario is INVINCIBLE " + counter + " - turns remain");
				counter -= 1;
			}
		}

		// return/print the console menu
		display.println("Mario " + this.printHp() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")");
		display.println("Wallet: " + this.wallet);
		display.println("");
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Gets current display character
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
	}

	/**
	 * This function add coins to the Player's wallet.
	 * @param coinValue The value of coin that we want to add to the Player's wallet.
	 */
	public void addToWallet(int coinValue) {
		if (coinValue > 0) {
			this.wallet += coinValue;
		}
	}

	/**
	 * This
	 * @param amount How much we want to deduct from Player's wallet.
	 * @see game.actions.BuyItemAction
	 * @return Whether the amount is successfully deducted from Player's wallet.
	 */
	public boolean deductFromWallet(int amount) {
		boolean valid = false;
		if (amount > 0 && this.wallet >= amount) {
			this.wallet -= amount;
			valid = true;
		}
		return valid;
	}
}
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
 */
public class Player extends Actor implements Resettable {
	private int counter = 10;
	private final Menu menu = new Menu();
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

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (this.hasCapability(Status.INVINCIBLE)) {
			int WEAR_OFF = 0;
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
		return menu.showMenu(this, actions, display);
	}

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
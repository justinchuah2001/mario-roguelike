package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;
import game.Status;

/**
 * A class that represents a coin, the currency for this game that allows the player to buy items.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 *
 */
public class Coin extends Item implements Resettable {
    private final int value;

    public Coin(int value){
        super("Coin", '$', true);
        this.value = value;
        this.addAction(new PickUpCoinAction(this));
        this.registerInstance();
    }

    public int getCoinValue(){
        return value;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESET)){
            currentLocation.removeItem(this);
        }
    }
}

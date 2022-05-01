package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A consumable item in the game that provides very strong and useful effects when used.
 * The catch to this item is that it only lasts for a limited duration!
 * @author Justin Chuah
 * @version 1.0
 */
public class PowerStar extends Consumables implements Buyable{
    /**
     * Counter that shows the star of the fading timer of the power star
     */
    private int counter = 10;
    /**
     * Value to show when the star fades and removed from the game
     */
    private static final int LIFESPAN = 1;
    /**
     * Price of the item
     */
    private final int  price ;
    /**
     * Integer used to reset the counter back to the original value after removal of Power Star
     */
    private final int RESET_VAL = 10;

    /**
     * Constructor
     */
    public PowerStar(){
        super("Power Star",'*', true);
        this.price = 600;
    }

    /**
     * Effects on the actor after consuming.
     * Heals and provides INVINCIBLE status
     * @param actor Actor consuming
     */
    @Override
    public void consumedEffect(Actor actor) {
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
    }

    /**
     * Used to update the counter every time a turn ends
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (counter == LIFESPAN){
            currentLocation.removeItem(this);
            resetCounter();
            System.out.println("Power Star has expired and removed. ");
        }
        else{
            counter-=1;
        }
    }

    /**
     * This function returns the price of PowerStar
     * @return Price of PowerStar.
     */
    public int getPrice() {
        return price;
    }

    @Override
    public void buy(Actor actor) {
        actor.addItemToInventory(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (counter == LIFESPAN ){
            actor.removeItemFromInventory(this);
        }else{
            counter -=1;
        }
    }

    /**
     * Methods to reset the counter back to original (10)
     */
    public void resetCounter(){
        this.counter= RESET_VAL;
    }

    /**
     * To display the remaining turns of power star before it fades away
     * @return A string that displays the counter
     */
    @Override
    public String toString(){
        return "Power Star - " + counter + " turns remaining";
    }

}

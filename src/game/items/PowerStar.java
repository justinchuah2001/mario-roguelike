package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class PowerStar extends Consumables implements Buyable{
    private int counter = 10;
    private static final int LIFESPAN = 1;
    private final int  price ;
    private final int RESET_VAL = 10;
    public PowerStar(){
        super("Power Star",'*', true);
        this.price = 600;
    }

    @Override
    public void consumedEffect(Actor actor) {
        super.consumedEffect(actor);
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
    }

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
    public void tick(Location currentLocation, Actor actor) {
        if (counter == LIFESPAN ){
            actor.removeItemFromInventory(this);
        }else{
            counter -=1;
        }
    }

    public void resetCounter(){
        this.counter= RESET_VAL;
    }

    @Override
    public String toString(){
        return "Power Star - " + counter + " turns remaining";
    }

}

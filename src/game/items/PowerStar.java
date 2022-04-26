package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;

public class PowerStar extends Consumables{
    private int counter = 1;
    private static final int LIFESPAN = 10;
    private final int  price ;
    public PowerStar(){
        super("Power Star",'*', true);
        this.price = 600;
    }

    @Override
    public void tick(Location currentLocation) {
        if (counter == LIFESPAN){
            currentLocation.removeItem(this);
        }
        else{
            counter+=1;
        }
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (counter == LIFESPAN){
            for (Item item: actor.getInventory()){
                if (item == this){
                    actor.getInventory().remove(item);
                }
            }
        }else{
            counter +=1;
        }
    }

}

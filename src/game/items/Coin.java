package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.Resettable;
import game.Status;

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
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}

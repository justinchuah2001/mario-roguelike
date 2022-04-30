package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;
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

}

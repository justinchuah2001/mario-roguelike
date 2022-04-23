package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;

public class Coin extends Item {
    private int value;

    public Coin(int value){
        super("Coin", '$', true);
        this.value = value;
        this.addAction(new PickUpCoinAction(this));
    }

    public int getCoinValue(){
        return value;
    }

}

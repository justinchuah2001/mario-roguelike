package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.PickUpCoinAction;

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

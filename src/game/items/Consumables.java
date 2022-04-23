package game.items;


import edu.monash.fit2099.engine.items.Item;

public abstract class Consumables extends Item {
    public Consumables(String name, char displayChar, boolean portable){
        super(name, displayChar,portable);
    }

}

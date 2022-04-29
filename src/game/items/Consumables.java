package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeItemAction;


public abstract class Consumables extends Item {

    private Actor actor;

    public Consumables(String name, char displayChar, boolean portable){
        super(name, displayChar,portable);
        this.addAction(new ConsumeItemAction(this));
    }
    public void consumedEffect(Actor actor) {
        this.actor = actor;

    }
}

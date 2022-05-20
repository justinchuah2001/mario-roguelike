package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public abstract class Water {

    private String name;

    public Water(String name){
        this.name = name;
    }

    public abstract void waterEffect(Actor actor);
}

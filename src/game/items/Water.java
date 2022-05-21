package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Water that is found at the fountain. It provides special effects upon consumption!
 * @author Justin Chuah
 * @version 1.0
 */
public abstract class Water {
    /**
     * Name of the water
     */
    private final String name;

    /**
     * Constructor
     * @param name Name of the type of water
     */
    public Water(String name){
        this.name = name;
    }

    /**
     * Effects that are taken upon the consumer
     * @param actor Actor receiving the effects
     */
    public abstract void waterEffect(Actor actor);

    /**
     * Prints the name of the water.
     * @return Name of water
     */
    @Override
    public String toString() {
        return this.name;
    }
}

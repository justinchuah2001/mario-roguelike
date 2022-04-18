package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Tree extends Ground {

    public int age;

    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);

    }

    @Override
    public void tick(Location location){
        super.tick(location);

        age++;
        if (age == 10){
            location.setGround(new Sapling());
        }
        else if (age == 20){
            location.setGround(new Mature());
        }
    }
}



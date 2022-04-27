package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.Status;

import java.util.Random;

public class Tree extends Ground implements Resettable {

    public int age;
    public Random r = new Random();


    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    @Override
    public void tick(Location location){
        super.tick(location);   // do nothing

        if (this.hasCapability(Status.RESET)){
            location.setGround(new Dirt());
            return ;
        }

        age++;
        if (age == 10){
            location.setGround(new Sapling());
        }
        else if (age == 20){
            location.setGround(new Mature());
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}



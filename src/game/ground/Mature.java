package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Koopa;
import game.items.Coin;

/**
 * A class that represents a Mature Tree, the third and final stage of a Tree.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Tree
 * */
public class Mature extends Tree {

    /**
     * A constructor for the Mature class
     * The age counter is reset to 0 so that it can be reused to spawn new sprouts
     */
    public Mature() {
        super('T');
        this.age = 0;
    }

    /**
     * Override of the toString method
     * @return A string with the name of the Ground
     */
    public String toString(){
        return "Mature Tree";
    }

    @Override
    public String jump(Actor actor, Location location) {
        return super.jump(actor, location);
    }

    /**
     * Every turn, there is a 20% chance for this Mature Tree to die
     * Every 5 turns, a new Sprout can spawn on an adjacent Dirt tile, then the age is reset to 0.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (r.nextInt(5) == 0) {
            location.setGround(new Dirt());
            return;
        }

        if (r.nextInt(20) < 3 && !location.containsAnActor()) {
            location.addActor(new Koopa());
        }
        if (age == 5 && hasFertileGround(location)) {
            spawnSprout(location);
            this.age = 0;
        }

        super.tick(location);
    }

    /**
     * This method spawns a sprout on an adjacent dirt tile. An adjacent tile is randomly selected and will be
     * reselected if it is an invalid option, such as being out of bounds or that it is not Dirt.
     *
     * @param location The location of the Mature Tree
     */
    private void spawnSprout(Location location) {
        int x = 0;
        int y = 0;
        do {
            switch (r.nextInt(8)) {
                case 0 -> {
                    x = location.x() - 1;
                    y = location.y() - 1;
                }
                case 1 -> y = location.y() - 1;
                case 2 -> {
                    x = location.x() + 1;
                    y = location.y() - 1;
                }
                case 3 -> x = location.x() - 1;
                case 4 -> x = location.x() + 1;
                case 5 -> {
                    x = location.x() - 1;
                    y = location.y() + 1;
                }
                case 6 -> y = location.y() + 1;
                case 7 -> {
                    x = location.x() + 1;
                    y = location.y() + 1;
                }
            }
        } while (!location.map().getXRange().contains(x) ||
                !location.map().getYRange().contains(y) ||
                !(location.map().at(x, y).getGround() instanceof Dirt));

        location.map().at(x, y).setGround(new Sprout());
    }

    /**
     * A failsafe so that Mature Tree does not attempt to spawn a sprout should there be an edge case where all
     * adjacent tiles to a Mature Tree are invalid, which would cause the random tile selection to infinitely loop
     * @param location The location of the Mature Tree
     * @return A boolean that dictates if there is at least one valid option to spawn a sprout
     */
    private boolean hasFertileGround(Location location) {
        for(int x = location.x() - 1; x < location.x() + 2; x++){
            for(int y = location.y() - 1; y < location.y() + 2; y++){
                if(location.map().getXRange().contains(x) && location.map().getYRange().contains(y) &&
                        location.map().at(x,y).getGround() instanceof Dirt){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getJumpSuccessRate() {
        return 70;
    }

    @Override
    public int getFallDamage() {
        return 30;
    }

    @Override
    public String getFlavourJump() {
        return "Yahoohoo!~ ";
    }

    @Override
    public String getFlavourFail() {
        return "Ouch! ";
    }

    @Override
    public String getFlavourDestroy() {
        return "No more Koopas from you! ";
    }
}



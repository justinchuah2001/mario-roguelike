package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.Resettable;
import game.Status;
import game.actions.JumpAction;

import java.util.Random;
/**
 * An abstract class that represents a Tree, which has 3 different stages that change with its age.
 *
 * @author Caelan Kao Khai Xuen
 * @version 1.0
 * @see game.ground.Sapling
 * @see game.ground.Sprout
 * @see game.ground.Mature
 * @see edu.monash.fit2099.engine.positions.Ground
 */
public abstract class Tree extends Ground implements Jumpable, Resettable {

    /**
     * the age of the tree
     */
    private int age;
    /**
     * a random number generator. to be used by child classes
     */
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
    public String jump(Actor actor, Location location) {
        return Jumpable.super.jump(actor, location);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        if (!location.containsAnActor()){
            return new ActionList(new JumpAction(this, location, direction));
        }
        return new ActionList();
    }

    @Override
    public boolean canActorEnter(Actor actor){
        if (actor.hasCapability(Status.FLYING)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * <p>The age of the Tree goes up by 1 every game tick and changes the tree to a sprout at age 10,
     * and a Mature at age 20 </p>
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if (this.hasCapability(Status.RESET)){
            if(r.nextInt(100) < 50){
                location.setGround(new Dirt());
            }
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
    public int getJumpSuccessRate() {
        return 0;
    }

    @Override
    public int getFallDamage() {
        return 0;
    }

    @Override
    public String getFlavourJump() {
        return null;
    }

    @Override
    public String getFlavourFail() {
        return null;
    }

    @Override
    public String getFlavourDestroy() {
        return null;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}



package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.DrinkBottleAction;

import java.util.ArrayList;

public class Bottle extends Item {
  private static ArrayList<Water> bottleContent = new ArrayList<>();
  private static Bottle instance;

  /**
   * Constructor.
   * Adds the action that allows the actor to consume the consumable
   */
  public Bottle() {
    super("Bottle", 'b', false);
    this.addCapability(Status.HAS_BOTTLE);
  }

  public void fillBottle(Water water){
    bottleContent.add(water);
  }

  public ArrayList<Water> getBottleContent(){
    return bottleContent;

  }

  public static Bottle getInstance(){
    if(instance == null){
      instance = new Bottle();
    }
    return instance;
  }

  @Override
  public String toString() {
    return "Bottle " + this.getBottleContent();
  }
}

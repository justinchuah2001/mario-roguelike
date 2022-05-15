import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.Random;

public class Monologue {
  private Random rand;
  private ArrayList<String> sentences;
  private boolean turnToTalk;
  private Actor actor;

  public Monologue(Actor actor){
    rand = new Random();
    sentences = new ArrayList<>();

    turnToTalk = rand.nextInt(2) == 1;
    this.actor = actor;
  }

  public void insertSentence(String sentence){
    this.sentences.add(sentence);
  }

  public void show(Display display){
    int totalSentences = this.sentences.size();
    if(totalSentences > 0 && turnToTalk) {
      String mySentence = this.sentences.get(rand.nextInt(totalSentences));
      String res = this.actor + ": \"" + mySentence + "\"";
      display.println(res);
    }

    turnToTalk = !turnToTalk;
  }
}

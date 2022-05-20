package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A class that implements monologue of the actors in the game.
 *
 * @author Chan Jia Zheng
 * @version 1.0
 * @see game.actors.Enemy
 * @see game.actors.Toad
 * @see game.actors.PrincessPeach
 */
public class Monologue {
  /**
   * Will be used to generate random number that decide whether the Actor should talk in first round.
   */
  private final static Random rand = new Random();
  /**
   * Collection of sentences that the Actor can talk.
   */
  private final ArrayList<String> sentences;
  /**
   * Indicator that shows whether the Actor should talk.
   */
  private boolean turnToTalk;
  /**
   * Actor who uses this Monologue.
   */
  private final Actor actor;

  /**
   * Constructor.
   * @param actor Actor who will use this Monologue.
   * @param sentences Collection of sentences that we want to add for the Actor to talk.
   */
  public Monologue(Actor actor, String[] sentences) {
    this.sentences = new ArrayList<>(Arrays.asList(sentences));
    this.turnToTalk = rand.nextInt(2) == 1;
    this.actor = actor;

  }

  /**
   * This function adds a sentence to the collection of the sentences that the Actor can talk.
   * @param sentence Sentence that we want to add for the Actor to talk.
   */
  public void addSentence(String sentence) {
    this.sentences.add(sentence);
  }

  /**
   * This function randomly prints a sentence on the Display in alternative turn.
   * @param display The Display to show the sentence.
   */
  public void show(Display display) {
    int totalSentences = this.sentences.size();
    if (turnToTalk && totalSentences > 0) {
      String mySentence = this.sentences.get(rand.nextInt(totalSentences));
      display.println(this.actor + ": \"" + mySentence + "\"");
    }

    this.turnToTalk = !this.turnToTalk;
  }
}

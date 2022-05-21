package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
  HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
  TALL,             // use this status to tell that current instance has "grown".
  DORMANT,          // use this status to show that the Koopa has now entered dormant state(not conscious).
  HAS_WRENCH,       // use this status to show that the Player now possess the wrench in his inventory.
  BUY_FROM_TOAD,    // use this status to show that the Player can buy from Toad
  TALK_TO_TOAD,     // use this status to show that the Player can talk to Toad
  PRE_DORMANT,      // use this status to show that the Koopa is able to enter dormant state(is conscious).
  INVINCIBLE,       // use this status to show that the Player is now under the effects of PowerStar
  RESET,            // use this status to show that the Item is resettable
  HAS_KEY,          // use this status to show that the Player now wields the Key to save the Princess
  FLYING,           // use this status to show that the actor is capable of flying across certain types of ground
  FINAL_BOSS,       // use this status to show that Bowser is the Final boss of the game
  SHOOTING_FIRE,    // use this status to show that the actor's attack spawns fire
  POWER_UP,         // use this status to show that the actor is under the effects of Power Water
  HAS_BOTTLE,       // use this status to show that the actor has the bottle in the inventory
  HEALING,          // use this status to show that the ground is Healing Fountain
  POWERING,         // use this status to show that the ground is Power Fountain
  IS_DEPLETED,      // use this status to show that the fountain's water has been depleted
  ON_FOUNTAIN,      // use this status to show that the actor is currently on a fountain
  WAS_COLLECTED,    // use this status to show that the fountain's was collected by the player using a bottle
  DRANK_FROM        // use this status to show that an actor aside from Player has drank the fountain's water
}

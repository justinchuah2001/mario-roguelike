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
  RESET,         // use this status to show that the Item is resettable
  HAS_KEY,
  FLYING,
  FINAL_BOSS,
  SHOOTING_FIRE    // actor's attack spawns fire
}

package p1_snl.service;

import lombok.ToString;
import p1_snl.models.DiceConfiguration;

@ToString
public class TraditionalDiceRoll implements IDiceRollStrategy {

  @Override
  public int roll(DiceConfiguration diceConfiguration) {
    int sumOfRolls = 0;
    for(int i = 0 ; i < diceConfiguration.getNumberOfDice(); i++) {
      sumOfRolls += (int)(Math.random() * (diceConfiguration.getDiceHighRange() - diceConfiguration.getDiceLowRange())
          + diceConfiguration.getDiceLowRange());
    }
    return sumOfRolls;
  }
}

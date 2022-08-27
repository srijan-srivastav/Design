package p1_snl.service;

import lombok.ToString;
import p1_snl.models.DiceConfiguration;

@ToString
public class SpecialSixDiceRoll implements IDiceRollStrategy{

  @Override
  public int roll(DiceConfiguration diceConfiguration) {
    return 0;
  }
}

package p1_snl.service;

import p1_snl.models.DiceConfiguration;

public interface IDiceRollStrategy {
  int roll(DiceConfiguration diceConfiguration);

}

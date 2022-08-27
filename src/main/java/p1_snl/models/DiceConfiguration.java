package p1_snl.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public final class DiceConfiguration {
  private final int diceLowRange;
  private final int diceHighRange;
  private final int numberOfDice;
}

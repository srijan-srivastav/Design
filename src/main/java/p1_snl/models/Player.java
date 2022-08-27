package p1_snl.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class Player {
  private final String name;
  @Setter
  private int currentPosition = 0;
  public Player(String name) {
    this.name = name;
  }
}

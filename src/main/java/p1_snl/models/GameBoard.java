package p1_snl.models;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import java.util.Collections;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@EqualsAndHashCode
@ToString
public class GameBoard implements IGameBoard{

  private final Map<Integer, Snake> snakeMappings;
  private final Map<Integer, Ladder> ladderMappings;
  private final Map<Integer, Player> playerMappings;
  @Getter
  private final DiceConfiguration diceConfiguration;
  @Getter
  private final int boardSize;

  @Inject
  private GameBoard(@Assisted Map<Integer, Snake> snakeMappings,
      @Assisted Map<Integer, Ladder> ladderMappings,
      @Assisted Map<Integer, Player> playerMappings,
      DiceConfiguration diceConfiguration,
      @Named("board.size") int boardSize) {
    this.snakeMappings = snakeMappings;
    this.ladderMappings = ladderMappings;
    this.playerMappings = playerMappings;
    this.diceConfiguration = diceConfiguration;
    this.boardSize = boardSize;
  }

  public int jumpFromPosition(int currentPosition) {
    int finalPosition = currentPosition;
    while(snakeMappings.containsKey(finalPosition) || ladderMappings.containsKey(finalPosition)) {
      if(snakeMappings.containsKey(finalPosition)) {
        finalPosition = snakeMappings.get(finalPosition).getTail();
        System.out.println("SNAKEEEEEEEEEEEEE");
      }
      else if(ladderMappings.containsKey(finalPosition)) {
        finalPosition = ladderMappings.get(finalPosition).getEnd();
        System.out.println("LADDDERRRRRRRRRRRRRRR");
      }
    }
    return finalPosition;
  }

  @Override
  public int getNumberOfPlayers() {
    return playerMappings.size();
  }

  @Override
  public Map<Integer, Player> getPlayerMap() {
    return Collections.unmodifiableMap(this.playerMappings);
  }

}

package p1_snl.service;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.Map;
import lombok.ToString;
import p1_snl.annotations.TraditionalDiceRoll;
import p1_snl.models.IGameBoard;
import p1_snl.models.Player;

@ToString
public class TraditionalGamePlayService implements IGamePlayService {

  IDiceRollStrategy diceRollStrategy;
  IGameBoard gameBoard;
  Map<Integer, Player> playerPositionMap;


  @Inject
  public TraditionalGamePlayService(@Assisted IGameBoard gameBoard,
      @TraditionalDiceRoll IDiceRollStrategy diceRollStrategy) {
    this.diceRollStrategy = diceRollStrategy;
    this.gameBoard = gameBoard;
    this.playerPositionMap = gameBoard.getPlayerMap();
  }

  @Override
  public void simulateGame() {
    boolean winnerFound = false;
    int playerIndex = 0;
    while (!winnerFound) {
      playerIndex = playerIndex % gameBoard.getNumberOfPlayers();
      int currentPosition = playerPositionMap.get(playerIndex).getCurrentPosition();
      int diceRoll = diceRoll();
      if (isValidRoll(playerIndex, currentPosition, diceRoll)) {
        int newPosition = newPosition(playerIndex, currentPosition, diceRoll);
        playerPositionMap.get(playerIndex).setCurrentPosition(newPosition);
        System.out.println(
            "Player: " + playerPositionMap.get(playerIndex).getName() + " rolled: " + diceRoll
                + " on position:" + currentPosition + " to move to position:" + newPosition);
        if (newPosition == gameBoard.getBoardSize()) {
          winnerFound = true;
          System.out.println(
              "Player: " + playerPositionMap.get(playerIndex).getName() + " Won the game!!");
        }
      } else {
        System.out.println(
            "Player: " + playerPositionMap.get(playerIndex).getName() + " rolled: " + diceRoll
                + " on position:" + currentPosition + " which is invalid");
      }
      playerIndex++;
    }
  }

  private boolean isValidRoll(int playerIndex, int currentPosition, int diceRoll) {
    return currentPosition + diceRoll <= gameBoard.getBoardSize();
  }

  private int newPosition(int playerIndex, int currentPosition, int diceRoll) {
    return gameBoard.jumpFromPosition(currentPosition + diceRoll);
  }

  private int diceRoll() {
    return diceRollStrategy.roll(gameBoard.getDiceConfiguration());
  }
}

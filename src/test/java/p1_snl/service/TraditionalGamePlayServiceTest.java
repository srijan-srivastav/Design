package p1_snl.service;


import static org.mockito.Mockito.when;

import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import p1_snl.models.DiceConfiguration;
import p1_snl.models.IGameBoard;
import p1_snl.models.Player;

@RunWith(MockitoJUnitRunner.class)
public class TraditionalGamePlayServiceTest {
  @Mock
  IDiceRollStrategy diceRollStrategy;
  @Mock
  IGameBoard gameBoard;
  @Mock
  DiceConfiguration diceConfiguration;

  Map<Integer, Player> playerPositionMap;

  @Before
  public void init() {
    playerPositionMap.put(0, new Player("Srijan"));
    playerPositionMap.put(1, new Player("Ruhi"));
    when(gameBoard.getNumberOfPlayers()).thenReturn(2);
    when(gameBoard.getDiceConfiguration()).thenReturn(diceConfiguration);
    when(diceRollStrategy.roll(gameBoard.getDiceConfiguration())).thenReturn(diceRoll());
  }

  @Test
  public void implementsTheRightInterface() {
    TraditionalGamePlayService service = new TraditionalGamePlayService(gameBoard, diceRollStrategy);
    Assert.assertTrue(service instanceof  IGamePlayService);
  }

  private int diceRoll() {
    return (int)(Math.random()*(5) + 1);
  }

}
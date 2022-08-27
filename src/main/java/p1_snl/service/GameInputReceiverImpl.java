package p1_snl.service;

import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.NonNull;
import p1_snl.models.GameBoard;
import p1_snl.models.IGameBoardFactory;
import p1_snl.models.Ladder;
import p1_snl.models.Player;
import p1_snl.models.Snake;

public class GameInputReceiverImpl implements IGameInputReceiver {

  private final Scanner scanner;
  private final Map<Integer, Snake> snakeMapping;
  private final Map<Integer, Ladder> ladderMapping;
  private final Map<Integer, Player> playersMapping;
  private final IGameBoardFactory<GameBoard> gameBoardFactory;



  @Inject
  private GameInputReceiverImpl(@NonNull IGameBoardFactory<GameBoard> gameBoardFactory) {
    this.scanner = new Scanner(System.in);
    this.snakeMapping = new HashMap<>();
    this.ladderMapping = new HashMap<>();
    this.playersMapping = new HashMap<>();
    this.gameBoardFactory = gameBoardFactory;
  }

  @Override
  public GameBoard processCommandLineInputToCreateGameBoard() {
    processSnakeMapping();
    processLadderMapping();
    processPlayerList();
    System.out.println(snakeMapping);
    System.out.println(ladderMapping);
    System.out.println(playersMapping);
    return gameBoardFactory.create(snakeMapping, ladderMapping, playersMapping);
  }

  @Override
  public GameBoard processInputFromFileToCreateGameBoard(String fileName) throws NotImplementedException {
    throw new NotImplementedException("This functionality is not available at the moment");
  }


  private void processSnakeMapping() {
    System.out.println("Enter number of Snakes: ");
    final int snakes = scanner.nextInt();
    for(int i = 0 ; i < snakes; i++) {
      System.out.println("Enter number at which head is present for Snake: "+  i);
      int head = scanner.nextInt();
      System.out.println("Enter number at which tail is present for Snake: "+  i);
      int tail = scanner.nextInt();
      snakeMapping.put(head, new Snake(head, tail));
    }
  }

  private void processLadderMapping() {
    System.out.println("Enter number of ladders: ");
    final int ladders = scanner.nextInt();
    for(int i = 0 ; i < ladders; i++) {
      System.out.println("Enter number at which top is present for ladder: "+  i);
      int start = scanner.nextInt();
      System.out.println("Enter number at which bottom is present for ladder: "+  i);
      int end = scanner.nextInt();
      ladderMapping.put(start, new Ladder(start, end));
    }

  }

  private void processPlayerList() {
    System.out.println("Enter number of players: ");
    final int players = scanner.nextInt();
    for(int i = 0; i < players; i++) {
      System.out.println("Enter name of player: " + i);
      playersMapping.put(i, new Player(scanner.next()));
    }
  }

//  @Override
//  public Map<Integer, Snake> getSnakeMappings() {
//    return Collections.unmodifiableMap(snakeMapping);
//  }
//
//  @Override
//  public Map<Integer, Ladder> getLadderMappings() {
//    return Collections.unmodifiableMap(ladderMapping);
//  }
//
//  @Override
//  public List<Player> getPlayerNames() {
//    return Collections.unmodifiableList(playersList);
//  }
}

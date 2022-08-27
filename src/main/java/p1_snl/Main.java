package p1_snl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import p1_snl.models.GameBoard;
import p1_snl.service.IGameInputReceiver;
import p1_snl.service.IGamePlayServiceFactory;
import p1_snl.service.TraditionalGamePlayService;

public class Main {

  public static void main(String[] args) {
    Injector guice = Guice.createInjector(new SNLModule());
    IGameInputReceiver inputReceiver = guice.getInstance(IGameInputReceiver.class);
    System.out.println("Input receiver created");
    GameBoard gameBoard = inputReceiver.processCommandLineInputToCreateGameBoard();
    IGamePlayServiceFactory<TraditionalGamePlayService> serviceFactory = guice.getInstance(Key.get(new TypeLiteral<IGamePlayServiceFactory<TraditionalGamePlayService>>(){}));
    TraditionalGamePlayService gamePlayService = serviceFactory.create(gameBoard);
    gamePlayService.simulateGame();
  }
}


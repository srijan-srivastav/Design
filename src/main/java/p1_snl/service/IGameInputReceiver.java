package p1_snl.service;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import p1_snl.models.GameBoard;

public interface IGameInputReceiver {
    GameBoard processCommandLineInputToCreateGameBoard();
    GameBoard processInputFromFileToCreateGameBoard(String fileName) throws NotImplementedException;

}

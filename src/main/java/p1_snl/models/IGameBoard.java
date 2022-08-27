package p1_snl.models;

import java.util.Map;

public interface IGameBoard {
    int jumpFromPosition(int currentPosition);
    int getNumberOfPlayers();
    Map<Integer, Player> getPlayerMap();
    DiceConfiguration getDiceConfiguration();
    int getBoardSize();

}

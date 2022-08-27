package p1_snl.models;

import java.util.List;
import java.util.Map;

public interface IGameBoardFactory<T> {
    T create(Map<Integer, Snake> snakeMappings,
        Map<Integer, Ladder> ladderMappings,
        Map<Integer, Player> playerMappings);
}

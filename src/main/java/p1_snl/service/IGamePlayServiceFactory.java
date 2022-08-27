package p1_snl.service;

import p1_snl.models.GameBoard;
import p1_snl.models.IGameBoard;

public interface IGamePlayServiceFactory<T> {
  T create(IGameBoard gameBoard);
}

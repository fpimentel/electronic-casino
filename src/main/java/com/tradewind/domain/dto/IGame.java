package com.tradewind.domain.dto;

import java.util.List;

import com.tradewind.domain.constant.GameStatus;
import com.tradewind.domain.constant.GameType;

/**
 * @author fpimentelc
 * Generic interface for the different type of games.
 */
public interface IGame {
	GameStatus getStatus();
	void setStatus(GameStatus gameStatus);
	List<IPlayer> getPlayers();
	GameType getGameType();
	void addPlayer(IPlayer player);
	int getNumbersOfPlayerToPlay();
	int getFeed();
}

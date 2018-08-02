package com.tradewind.domain.dto;

import com.tradewind.domain.constant.GameType;

/**
 * @author fpimentelc
 * This class represent the request to establish a new game in the CASINO.
 * initially this game does not have any player.
 */
public class EstablishGameRequest {
	private GameType gameType;

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
}

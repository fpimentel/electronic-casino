package com.tradewind.domain.dto;

import com.tradewind.domain.constant.GameType;

/**
 * @author fpimentelc
 * @version 1.0
 * Request class for join to a game in the CASINO.
 */
public class JoinToAGameRequest {
	private CasinoPlayerDTO player;
	private GameType gameType;
	
	public CasinoPlayerDTO getPlayer() {
		return player;
	}
	public void setPlayer(CasinoPlayerDTO player) {
		this.player = player;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
}

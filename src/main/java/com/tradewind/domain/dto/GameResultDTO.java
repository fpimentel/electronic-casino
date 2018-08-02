package com.tradewind.domain.dto;

import com.tradewind.domain.constant.GameResult;

/**
 * @author fpimentelc
 *	This class will hold the result of the game. If there is a winner will have the player, 
 *  if itsTie the player will be null because this belong to the CASINO.
 */
public class GameResultDTO {

	private IPlayer winnerPlayer;
	private GameResult gameResult;
	
	public IPlayer getWinnerPlayer() {
		return winnerPlayer;
	}
	public void setWinnerPlayer(IPlayer winnerPlayer) {
		this.winnerPlayer = winnerPlayer;
	}
	public GameResult getGameResult() {
		return gameResult;
	}
	public void setGameResult(GameResult gameResult) {
		this.gameResult = gameResult;
	}
}

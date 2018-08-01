package com.tradewind.domain.dto;

import java.util.List;
import java.util.UUID;


import com.tradewind.domain.constant.GameStatus;
import com.tradewind.domain.constant.GameType;

public class RPSGameDTO implements IGame{
	private UUID gameId;
	private int entryFeed;
	private List<IPlayer> players;
	private GameStatus gameStatus;
	private GameType gameType = GameType.RPSGAME;
	private int numberOfPlayerToPlay;
	public RPSGameDTO() {
		super();
		this.gameId = UUID.randomUUID();
	}
	public UUID getGameId() {
		return gameId;
	}
	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}

	@Override
	public GameStatus getStatus() {
		return gameStatus;
	}
	@Override
	public void setStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;		
	}
	@Override
	public List<IPlayer> getPlayers() {
		return players;
	}
	@Override
	public GameType getGameType() {
		return gameType;
	}
	@Override
	public void addPlayer(IPlayer player) {
		this.players.add(player);		
	}
	@Override
	public int getNumbersOfPlayerToPlay() {
		return numberOfPlayerToPlay;
	}
	@Override
	public int getFeed() {
		return this.entryFeed;
	}
}

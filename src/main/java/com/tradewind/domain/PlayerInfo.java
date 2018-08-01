package com.tradewind.domain;

import java.util.UUID;

public class PlayerInfo {
	private Strategy strategy;
	private int accountBalance;
	private UUID playerId;
	
	public PlayerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlayerInfo(Strategy strategy, int accountBalance, UUID playerId) {
		super();
		this.strategy = strategy;
		this.accountBalance = accountBalance;
		this.playerId = playerId;
	}
	
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public UUID getPlayerId() {
		return playerId;
	}
	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}
}

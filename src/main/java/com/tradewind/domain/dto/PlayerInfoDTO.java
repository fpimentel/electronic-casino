package com.tradewind.domain.dto;

import com.tradewind.domain.Strategy;

/**
 * @author fpimentelc
 *
 */
public class PlayerInfoDTO {
	private Strategy strategy;
	int accountBalance;
	private String playerId;
	
	public PlayerInfoDTO() {
		super();
	}
	public PlayerInfoDTO(Strategy strategy, int accountBalance, String playerId) {
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
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
}

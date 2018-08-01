package com.tradewind.domain.entity;

import static javax.persistence.EnumType.STRING;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import com.tradewind.domain.constant.GameType;

/**
 * @author fpimentelc
 * @This is the db table representation for the player in the CASINO. This will hold the data from/to db.
 * @version 1.0
 */
@Entity
@Table(name = "CASINO_PLAYER")
public class CasinoPlayer {
	@Id
	private UUID playerId;
	
	@Enumerated(STRING)
	@Column(nullable = true)
	private GameType gameToPlay;
	
	private int accountBalance;
	
	public CasinoPlayer() {
		super();
		this.playerId = UUID.randomUUID();
	}

	public GameType getGameToPlay() {
		return gameToPlay;
	}

	public void setGameToPlay(GameType gameToPlay) {
		this.gameToPlay = gameToPlay;
	}

	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
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
}
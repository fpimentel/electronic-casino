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
 * @version 1.0
 * Entity class that represent the game's catalog in the CASINO.
 */
@Entity
@Table(name = "GAMES")
public class Game {
	@Id
	private String gameId;
	
	@Column(name ="ENTRY_FEED", nullable = false)
	private int entryFeed;
	
	@Enumerated(STRING)
	@Column(nullable = false)
	private GameType gameType;
	
	@Column(nullable = false)
	private int numberOfPlayerToPlay;
	
    public Game() {
		super();
		this.gameId = UUID.randomUUID().toString();
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getEntryFeed() {
		return entryFeed;
	}

	public void setEntryFeed(int entryFeed) {
		this.entryFeed = entryFeed;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		return true;
	}
}

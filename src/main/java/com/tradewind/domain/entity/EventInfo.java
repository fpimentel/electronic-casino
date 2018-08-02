package com.tradewind.domain.entity;

import static javax.persistence.EnumType.STRING;

import java.time.Instant;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tradewind.domain.constant.EventType;
import com.tradewind.domain.constant.GameType;

import javax.persistence.Column;
/**
 * @author fpimentelc
 *Entity class that represent the EVENT_INFO db table. This will hold all the event occured in the CASINO.
 */
@Entity
@Table(name = "EVENT_INFO")
public class EventInfo {
	@Id
	private String eventId;
	
	@Column(name ="PLAYER_ID", nullable = true)
	private String playerId;
	
	@Column(name ="DEALER_ID", nullable = true)
	private String dealerId;
	
	@Enumerated(STRING)
	@Column(nullable = true)
	private EventType eventType;
	
	@Enumerated(STRING)
	@Column(nullable = true)
	private GameType gameType;
	
    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();
    
    
	public EventInfo() {
		super();
		this.eventId = UUID.randomUUID().toString();
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public String getEventId() {
		return eventId;
	}


	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


	public String getPlayerId() {
		return playerId;
	}


	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}


	public EventType getEventType() {
		return eventType;
	}


	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	public Instant getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
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
		EventInfo other = (EventInfo) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		return true;
	}
}

package com.tradewind.domain.dto;

import com.tradewind.domain.constant.Status;

/**
 * @author fpimentelc
 * Data transfer object for Dealer entity.
 */
public class DealerDTO implements IDealer {
	private String dealerId;
	private IGame game;
	private Status status;
	
	public DealerDTO() {
		super();
		this.status = Status.AVAILABLE;
	}

	public IGame getGame() {
		return game;
	}

	public void setGame(IGame game) {
		this.game = game;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	@Override
	public String getDealerId() {
		return this.dealerId;
	}
}

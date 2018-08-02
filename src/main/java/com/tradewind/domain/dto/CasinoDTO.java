package com.tradewind.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CasinoDTO {
	private  Map<String, List<IGame>> availableRPSGames = new ConcurrentHashMap<>();
	private int houseAccount = 100000;
	private List<DealerDTO> dealers = new ArrayList<>();
	
	public Map<String, List<IGame>> getAvailableRPSGames() {
		return availableRPSGames;
	}

	public void setAvailableRPSGames(Map<String, List<IGame>> availableRPSGames) {
		this.availableRPSGames = availableRPSGames;
	}

	public int getHouseAccount() {
		return houseAccount;
	}

	public void setHouseAccount(int houseAccount) {
		this.houseAccount = houseAccount;
	}

	public List<DealerDTO> getDealers() {
		return dealers;
	}

	public void setDealers(List<DealerDTO> dealers) {
		this.dealers = dealers;
	}
}

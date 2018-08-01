package com.tradewind.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradewind.domain.constant.GameType;
import com.tradewind.domain.dto.IGame;
import com.tradewind.domain.dto.RPSGameDTO;

@Service
public class ElectronicCasinoServiceImpl implements IElectronicCasinoService{
	
	@Autowired
	private IDealerService dealerService;
	public static Map<String, List<IGame>> availableRPSGames = new ConcurrentHashMap<>();
	// This is to simulate the house account in the CASINO but in real scenario we need to use the db and save this in the table.
	public static int houseAccount = 0;
	
	@PostConstruct
	//TODO: load from db.
	private void intitialize() {
		List<IGame> games = new ArrayList<>();
		games.add(new RPSGameDTO());
		games.add(new RPSGameDTO());
		games.add(new RPSGameDTO());
		availableRPSGames.put(GameType.RPSGAME.name(),games);
	}
	
	@Override
	public void establishNewGame() {
		dealerService.establishNewGame();
	}
}

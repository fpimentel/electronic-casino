package com.tradewind.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradewind.domain.constant.GameType;
import com.tradewind.domain.dto.CasinoDTO;
import com.tradewind.domain.dto.DealerDTO;
import com.tradewind.domain.dto.EstablishGameRequest;
import com.tradewind.domain.dto.IGame;
import com.tradewind.domain.dto.RPSGameDTO;

@Service
public class ElectronicCasinoServiceImpl implements IElectronicCasinoService{
	
	@Autowired
	private IDealerService dealerService;
	
 
	public static CasinoDTO casinoDTO = new CasinoDTO();

	//To simulate we going to cache some information about the casino, dealers and games but in real app this should be load from db and proceed to
	//do the data transfers to the corresponding DTOs.
	@PostConstruct
	private void intitialize() {
		
		List<IGame> games = new ArrayList<>();
		RPSGameDTO rpsGame1 = new RPSGameDTO();
		rpsGame1.setEntryFeed(10);
		rpsGame1.setNumberOfPlayerToPlay(2);
		RPSGameDTO rpsGame2 = new RPSGameDTO();
		rpsGame2.setEntryFeed(10);
		rpsGame2.setNumberOfPlayerToPlay(2);
		RPSGameDTO rpsGame3 = new RPSGameDTO();
		rpsGame3.setEntryFeed(10);
		rpsGame3.setNumberOfPlayerToPlay(2);
		games.add(rpsGame1);
		games.add(rpsGame2);
		games.add(rpsGame3);
		casinoDTO.getAvailableRPSGames().put(GameType.RPSGAME.name(),games);
		DealerDTO dealer1 = new DealerDTO();
		dealer1.setDealerId("234354sfasfdasf");
		DealerDTO dealer2 = new DealerDTO();
		dealer2.setDealerId("234354sfasfdasf2");
		DealerDTO dealer3 = new DealerDTO();
		dealer3.setDealerId("234354sfasfdasf3");
		casinoDTO.getDealers().add(dealer1);
		casinoDTO.getDealers().add(dealer2);
		casinoDTO.getDealers().add(dealer3);
	}
	
	@Override
	public void establishNewGame(EstablishGameRequest establishGameRequest) {
		dealerService.establishNewGame(establishGameRequest);
	}
	public void makeGameAvailable(GameType gameType) {
		switch(gameType) {
		case RPSGAME:
			RPSGameDTO rpsGame = new RPSGameDTO();
			rpsGame.setEntryFeed(10);
			rpsGame.setNumberOfPlayerToPlay(2);
			ElectronicCasinoServiceImpl.casinoDTO.getAvailableRPSGames().get(gameType.name()).add(rpsGame);
			break;
		}
	}
}

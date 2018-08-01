package com.tradewind.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import com.tradewind.domain.dto.RPSGameDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradewind.domain.constant.EventType;
import com.tradewind.domain.constant.GameResult;
import com.tradewind.domain.constant.GameStatus;
import com.tradewind.domain.constant.GameType;
import com.tradewind.domain.constant.Status;
import com.tradewind.domain.dto.DealerDTO;
import com.tradewind.domain.dto.IGame;
import com.tradewind.domain.dto.JoinToAGameRequest;
import com.tradewind.domain.entity.EventInfo;
import com.tradewind.domain.entity.Game;
import com.tradewind.domain.repository.EventInfoRepository;
import com.tradewind.domain.repository.GameRepository;

/**
 * @author fpimentelc
 * @version 1.0
 *This class will hold all the business logic for dealer operations
 */
@Service
public class DealerService implements IDealerService {
	private final Logger log = LoggerFactory.getLogger(DealerService.class);
	public static int dealerQuantity = 3;
	private List<DealerDTO> availableDealers = new ArrayList<>();
	
	@Autowired
	private EventInfoRepository eventInfoRepository;
	
	@Autowired
	private GameRepository gameRepository;
		
	@PostConstruct
	private void initialize() {
		availableDealers.add(new DealerDTO());
		availableDealers.add(new DealerDTO());
		availableDealers.add(new DealerDTO());
	}
	
	public boolean establishNewGame() {
		Optional<DealerDTO> dealer = findAvailableDealer();
		if(dealer.isPresent()){
			//TODO: sent the gameType to this method to check the available games in the casino.
			//takeAvailableGameFromCasino
			EventInfo eventInfo = logEventInDb(dealer,EventType.ESTABLISH_NEW_GAME);
			eventInfoRepository.save(eventInfo);
			return true;
		}
		log.info("Theres not available Dealer to establish new games.");
		return false;
	}

	private EventInfo logEventInDb(Optional<DealerDTO> dealer,EventType eventType) {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setEventType(eventType);
		dealer.get().setGame(new RPSGameDTO());
		return eventInfo;
	}

	public boolean takeAvailableGameFromCasino(JoinToAGameRequest joinToAGameRequest) {
		 List<IGame> availableGamesInCasino = ElectronicCasinoServiceImpl.availableRPSGames.get(joinToAGameRequest.getGameType().name());
		 Optional<IGame> availableGame = availableGamesInCasino.stream().filter(game -> game.getStatus() == GameStatus.AVAILABLE).findFirst();
		 if(availableGame.isPresent()){
			 availableGame.get().setStatus(GameStatus.TAKEN);
			 return true;
		 }
		 return false;
	}
	
	public Optional<DealerDTO> findAvailableDealer() {
		synchronized(this){
			Optional<DealerDTO> availableDealer = availableDealers.stream().filter(dealer -> dealer.getStatus() == Status.AVAILABLE).findFirst();
			availableDealer.ifPresent(del -> del.setStatus(Status.WORKING));
			return availableDealer;
		}
	}

	public Optional<DealerDTO> findDealerWithPendingPlayer(GameType gameType) {
		synchronized(this){
			Optional<DealerDTO> availableDealer = availableDealers.stream()
									.filter(dealer -> dealer.getStatus() == Status.WORKING 
												&& dealer.getGame().getPlayers().size() <= 1 
												&& dealer.getGame().getGameType() == gameType)
									.findFirst();
			return availableDealer;
		}
	}
	
	@Override
	public boolean hasSufficientAmount(JoinToAGameRequest joinToAGameRequest) {
		Integer accountBalance = Optional.ofNullable(joinToAGameRequest.getPlayer())
										.map(player -> player.getPlayeInfoDto())
										.map(playerInf -> playerInf.getAccountBalance()).get();
			
		Game gameFromDb = gameRepository.findByGameType(joinToAGameRequest.getGameType());
		if(accountBalance != null && accountBalance >= gameFromDb.getEntryFeed()) {
			return true;
		}
		return false;
	}

	public void joinToAStablishedGame(JoinToAGameRequest joinToAGameRequest) {
		Optional<DealerDTO> findDealerWithPendingPlayer = findDealerWithPendingPlayer(joinToAGameRequest.getGameType());
		if(findDealerWithPendingPlayer.isPresent()){
			DealerDTO dealerWithPendingPlayer = findDealerWithPendingPlayer.get();
			IGame gameToPlay = dealerWithPendingPlayer.getGame();
			gameToPlay.addPlayer(joinToAGameRequest.getPlayer());
			logEventInDb(findDealerWithPendingPlayer,EventType.JOING_GAME);
			if(gameToPlay.getPlayers().size() == gameToPlay.getNumbersOfPlayerToPlay()) {
				GameResult result = startRPSGame(findDealerWithPendingPlayer.get());
				if(result == GameResult.TIE) {
					ElectronicCasinoServiceImpl.houseAccount += gameToPlay.getFeed() * gameToPlay.getPlayers().size();
				}else {
					// TODO
				}
			}
		}
	}

	/**
	 * This is a dummy method that randomly return TIE or WINNER to simulate that the game finished.
	 */
	public GameResult startRPSGame(DealerDTO dealer) {
		List<GameResult> results = new ArrayList<>();
		results.add(GameResult.TIE);
		results.add(GameResult.WINNER);
		int randomIndex = (int)(Math.random()*((1-0)+1))+0;
		return results.get(randomIndex);
	}
}

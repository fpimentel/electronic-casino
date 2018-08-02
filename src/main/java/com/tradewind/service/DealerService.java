package com.tradewind.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
import com.tradewind.domain.dto.EstablishGameRequest;
import com.tradewind.domain.dto.GameResultDTO;
import com.tradewind.domain.dto.IGame;
import com.tradewind.domain.dto.IPlayer;
import com.tradewind.domain.dto.JoinToAGameRequest;
import com.tradewind.domain.entity.EventInfo;
import com.tradewind.domain.repository.EventInfoRepository;

/**
 * @author fpimentelc
 * @version 1.0
 *This class will hold all the business logic for dealer operations
 */
@Service
public class DealerService implements IDealerService {
	private final Logger log = LoggerFactory.getLogger(DealerService.class);
	public static int dealerQuantity = 3;
	
	@Autowired
	private EventInfoRepository eventInfoRepository;
	
	@Autowired
	private IElectronicCasinoService electronicCasino;
	
	public boolean establishNewGame(EstablishGameRequest establishGameRequest) {
		synchronized(this){
			//proceed to find a the game to establish in the game from CASINO and remove it from the list of game availables.
			List<IGame> availableGames = ElectronicCasinoServiceImpl.casinoDTO.getAvailableRPSGames().get(establishGameRequest.getGameType().name());
			if(availableGames.size() > 0) {
				IGame gameFromCasino = availableGames.remove(0);
				Optional<DealerDTO> dealer = findAvailableDealer();
				if(dealer.isPresent()){
					dealer.get().setGame(gameFromCasino);
					EventInfo eventInfo = mapToEventEntityInDb(dealer.get(),EventType.ESTABLISH_NEW_GAME);
					eventInfoRepository.save(eventInfo);
					return true;
				}
			}else {
				log.info("Theres not available games in the casino.");
				return false;
			}
		}
		log.info("Theres not available Dealer to establish new games.");
		return false;
	}

	private EventInfo mapToEventEntityInDb(DealerDTO dealer,EventType eventType) {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setEventType(eventType);
		eventInfo.setDealerId(dealer.getDealerId());
		if(dealer.getGame() != null) {
			eventInfo.setGameType(dealer.getGame().getGameType());
		}
		return eventInfo;
	}

	private EventInfo mapToEventEntityInDb(DealerDTO dealer,EventType eventType, String newPlayerId) {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setEventType(eventType);
		eventInfo.setDealerId(dealer.getDealerId());
		eventInfo.setPlayerId(newPlayerId);
		if(dealer.getGame() != null) {
			eventInfo.setGameType(dealer.getGame().getGameType());
		}
		return eventInfo;
	}
	
	public boolean takeAvailableGameFromCasino(JoinToAGameRequest joinToAGameRequest) {
		 List<IGame> availableGamesInCasino = ElectronicCasinoServiceImpl.casinoDTO.getAvailableRPSGames().get(joinToAGameRequest.getGameType().name());
		 Optional<IGame> availableGame = availableGamesInCasino.stream().filter(game -> game.getStatus() == GameStatus.AVAILABLE).findFirst();
		 if(availableGame.isPresent()){
			 availableGame.get().setStatus(GameStatus.TAKEN);
			 return true;
		 }
		 return false;
	}
	
	public Optional<DealerDTO> findAvailableDealer() {
		synchronized(this){
			Optional<DealerDTO> availableDealer = ElectronicCasinoServiceImpl.casinoDTO.getDealers().stream().filter(dealer -> dealer.getStatus() == Status.AVAILABLE).findFirst();
			availableDealer.ifPresent(del -> del.setStatus(Status.WORKING));
			return availableDealer;
		}
	}

	public Optional<DealerDTO> findDealerWithPendingPlayer(GameType gameType) {
		synchronized(this){
			final Predicate<? super DealerDTO> isDealerIsWaitingForPlayer = dealer -> dealer.getStatus() == Status.WORKING 
						&& dealer.getGame()==null && dealer.getGame().getPlayers() == null
						|| ( 
							   dealer.getGame() != null
							&& dealer.getGame().getPlayers() != null 
							&& dealer.getGame().getGameType() != null
							&& dealer.getGame().getPlayers().size() <= 1 
							&& dealer.getGame().getGameType() == gameType
						   );
			
			Optional<DealerDTO> availableDealer = ElectronicCasinoServiceImpl.casinoDTO.getDealers().stream()
									.filter(isDealerIsWaitingForPlayer)
									.findFirst();
			return availableDealer;
		}
	}
	
	@Override
	public boolean hasSufficientAmount(JoinToAGameRequest joinToAGameRequest) {
		Integer accountBalance = Optional.ofNullable(joinToAGameRequest.getPlayer())
										.map(player -> player.getPlayeInfoDto())
										.map(playerInf -> playerInf.getAccountBalance()).get();
		//This should be the query to the database but we have a mock scenario with the casino,dealers and games, so,
		//we don't use this query from the db, we query the information from our mock cached instead.
		//Game gameFromDb = gameRepository.findByGameType(joinToAGameRequest.getGameType()).getEntryFeed();
		Integer entryFeed = findEntryFeed(joinToAGameRequest.getGameType());
		if(entryFeed != null) {
			if(accountBalance != null && accountBalance >= entryFeed) {
				return true;
			}
		}
		return false;
	}

	private Integer findEntryFeed(GameType gameType) {
		Integer entryFeed = Optional.ofNullable(ElectronicCasinoServiceImpl.casinoDTO.getAvailableRPSGames().get(gameType.name()))
		.map(gamesList -> gamesList.get(0)).map(game -> game.getFeed())
		.orElse(null);
		return entryFeed;
	}

	public boolean joinToAStablishedGame(JoinToAGameRequest joinToAGameRequest) {
		Optional<DealerDTO> findDealerWithPendingPlayer = findDealerWithPendingPlayer(joinToAGameRequest.getGameType());
		if(findDealerWithPendingPlayer.isPresent()){
			DealerDTO dealerWithPendingPlayer = findDealerWithPendingPlayer.get();
			IGame gameToPlay = dealerWithPendingPlayer.getGame();
			gameToPlay.addPlayer(joinToAGameRequest.getPlayer());
			mapToEventEntityInDb(dealerWithPendingPlayer,EventType.JOING_GAME, joinToAGameRequest.getPlayer().getPlayeInfoDto().getPlayerId());
			log.warn("Player Joined succefully to a game: {} ", gameToPlay.getGameType().name());
			if(gameToPlay.getPlayers().size() == gameToPlay.getNumbersOfPlayerToPlay()) {
				GameResultDTO result = startRPSGameAndGetWinner(findDealerWithPendingPlayer.get());
				int winningBalance = getWinningBalance(gameToPlay);
				applyPayOut(result, winningBalance);
				electronicCasino.makeGameAvailable(joinToAGameRequest.getGameType());//after the game finish the game should be available in the casino again
			}
		}else {
			log.warn("There are not established games in the casino....");
			return false;
		}
		return true;
	}

	private void applyPayOut(GameResultDTO result, int winningBalance) {
		if(result.getGameResult() == GameResult.TIE) {
			int currentBalance = ElectronicCasinoServiceImpl.casinoDTO.getHouseAccount();
			ElectronicCasinoServiceImpl.casinoDTO.setHouseAccount(currentBalance + winningBalance);
		}else {
			IPlayer winnerPlayer = result.getWinnerPlayer();
			int currentPlayerBalance = winnerPlayer.getAccontBalance();
			winnerPlayer.setAccountBalance(currentPlayerBalance + winningBalance);
		}
	}

	/**
	 * @param gameToPlay
	 * @return The balance that the winner should get after the game end. This balance belong to the casino or Player.
	 */
	private int getWinningBalance(IGame gameToPlay) {
		int newBalance =  gameToPlay.getFeed() * gameToPlay.getPlayers().size();
		return newBalance;
	}

	/**
	 * This is a dummy method that randomly return TIE or WINNER to simulate that the game finished.
	 */
	private GameResultDTO startRPSGameAndGetWinner(DealerDTO dealer) {
		GameResultDTO gameResultDTO = new GameResultDTO();
		List<GameResult> results = new ArrayList<>();
		results.add(GameResult.TIE);
		results.add(GameResult.WINNER);
		int randomIndex = (int)(Math.random()*((1-0)+1))+0;
		GameResult gameResult = results.get(randomIndex);
		gameResultDTO.setGameResult(gameResult);
		if(gameResult == GameResult.WINNER) {
			gameResultDTO.setWinnerPlayer(dealer.getGame().getPlayers().get(randomIndex));
		}
		return gameResultDTO;
	}
}

package com.tradewinds.domain;

import com.tradewind.domain.dto.IGame;
import com.tradewind.domain.entity.CasinoPlayer;

/**
 * @author fpimentelc
 *Util class for MockDomain creations. This will be usefull for the JUNI.
 */
public class MockDomain {
	
	public static CasinoPlayer createCasinoPlayer(int accountBalance, IGame gameToPlay) {
		CasinoPlayer player = new CasinoPlayer();
		player.setAccountBalance(accountBalance);
		//player.setGameToPlay(new RPSGame());
		return player;
	}
}

package com.tradewind.service;

import com.tradewind.domain.constant.GameType;
import com.tradewind.domain.dto.EstablishGameRequest;

public interface IElectronicCasinoService {
	void establishNewGame(EstablishGameRequest establishGameRequest);
	void makeGameAvailable(GameType gameType);
}

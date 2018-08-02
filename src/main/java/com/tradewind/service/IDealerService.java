package com.tradewind.service;

import com.tradewind.domain.dto.EstablishGameRequest;
import com.tradewind.domain.dto.JoinToAGameRequest;

public interface IDealerService {
	boolean establishNewGame(EstablishGameRequest establishGameRequest);
	boolean hasSufficientAmount(JoinToAGameRequest joinToAGameRequest);
}

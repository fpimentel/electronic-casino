package com.tradewind.service;

import com.tradewind.domain.dto.JoinToAGameRequest;

public interface IDealerService {
	boolean establishNewGame();
	boolean hasSufficientAmount(JoinToAGameRequest joinToAGameRequest);
}

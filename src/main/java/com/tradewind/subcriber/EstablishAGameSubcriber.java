package com.tradewind.subcriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradewind.domain.dto.EstablishGameRequest;
import com.tradewind.service.IElectronicCasinoService;

/**
 * @author fpimentelc
 * Class that is observing the incoming message from EstablishAGamePublisher
 * @see EstablishAGamePublisher
 */
@Component
public class EstablishAGameSubcriber {
	
	@Autowired
	private IElectronicCasinoService electronicCasinoService;
	
	@RabbitListener(queues="${establish_new_game_queue.name}")
    public void recievedMessage(EstablishGameRequest establishGameRequest) {
		System.out.println("EstablishGame message received for game: " + establishGameRequest.getGameType().name());
		electronicCasinoService.establishNewGame(establishGameRequest);
    }
}

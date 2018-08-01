package com.tradewind.subcriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tradewind.domain.dto.JoinToAGameRequest;
import com.tradewind.service.DealerService;
import com.tradewind.service.IElectronicCasinoService;

/**
 * @author fpimentelc
 * @see SentGameResultToCasinoPublisher
 * @version 1.0
 * Class that is observing the incoming message from SentGameResultToCasinoPublisher
 */
@Component
public class SentGameResultToCasinoSubcriber {
	
	@Autowired
	private IElectronicCasinoService electronicCasinoService;
	@Autowired
	private DealerService dealerService;
	
	@RabbitListener(queues="${join_new_player.queue.name}")
    public void recievedMessage(JoinToAGameRequest joinToAGameRequest) {
		if(dealerService.hasSufficientAmount(joinToAGameRequest)) {
			dealerService.joinToAStablishedGame(joinToAGameRequest);
		}
    }
}

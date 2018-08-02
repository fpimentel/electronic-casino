package com.tradewind.subcriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tradewind.domain.dto.JoinToAGameRequest;
import com.tradewind.service.DealerService;

/**
 * @author fpimentelc
 * @see JoinToAGamePublisher
 * @version 1.0
 * Class that is observing the incoming message from JoinToAGamePublisher
 */
@Component
public class JoinToAGameSubcriber {
	
	@Autowired
	private DealerService dealerService;
	private final Logger log = LoggerFactory.getLogger(JoinToAGameSubcriber.class);
	@RabbitListener(queues="${join_new_player.queue.name}")
    public void recievedMessage(JoinToAGameRequest joinToAGameRequest) {
		if(dealerService.hasSufficientAmount(joinToAGameRequest)) {
			dealerService.joinToAStablishedGame(joinToAGameRequest);
		}else {
			log.warn("The player does not have sufficient funds. Player information is: {}" , joinToAGameRequest);
		}
    }
}

package com.tradewind.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fpimentelc
 * Publish the message to the broker with the result of the game.
 */
@Component
public class SentGameResultToCasinoPublisher {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${electronic_casino_exchange.name}")
	private String exchange;
	@Value("${electronic_casino_exchange.sent_game_result.rounting_key.name}")
	private String sentGameResultRoutingKey;
	
	public void sentSentGameResultToCasino(String establishANewGameMessage){
		System.out.println("Rounting key for sentGameResult " + sentGameResultRoutingKey);
		amqpTemplate.convertAndSend(exchange, sentGameResultRoutingKey,establishANewGameMessage);
	}
}
package com.tradewind.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fpimentelc
 * @version 1.0
 * Class that sent a message to the broker to establish a new a game in the CASINO.
 */
@Component
public class EstablishAGamePublisher {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${electronic_casino_exchange.name}")
	private String exchange;
	@Value("${electronic_casino_exchange.establish_new_game_routing_key.name}")
	private String establishNewGameRoutingKey;
	
	public void sentStablishANewGameMessage(String establishANewGameMessage){
		System.out.println("ROunting key for stablis a new game " + establishNewGameRoutingKey);
		amqpTemplate.convertAndSend(exchange, establishNewGameRoutingKey,establishANewGameMessage);
	}
}

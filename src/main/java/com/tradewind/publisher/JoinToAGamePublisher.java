package com.tradewind.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.tradewind.domain.dto.JoinToAGameRequest;

/**
 * @author fpimentelc
 * @version 1.0
 * Class that sent a message to the broker to join to a game.
 */
@Component
public class JoinToAGamePublisher {
		
		@Autowired
		private AmqpTemplate amqpTemplate;
		
		@Value("${electronic_casino_exchange.name}")
		private String exchange;
		@Value("${electronic_casino_exchange.join_new_player.rounting_key.name}")
		private String joinToAGameRoutingKey;
		
		public void sentJoinToAGameMessage(JoinToAGameRequest joinToAGameRequest){
			System.out.println("Sending a new message to join the game: " + joinToAGameRequest.getGameType().name());
			amqpTemplate.convertAndSend(exchange, joinToAGameRoutingKey,joinToAGameRequest);
		}
}

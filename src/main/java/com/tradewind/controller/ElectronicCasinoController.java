package com.tradewind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tradewind.domain.constant.EventType;
import com.tradewind.domain.dto.EstablishGameRequest;
import com.tradewind.domain.dto.JoinToAGameRequest;
import com.tradewind.publisher.EstablishAGamePublisher;
import com.tradewind.publisher.JoinToAGamePublisher;

/**
 * @author fpimentelc
 * This will sent a message to start a game.
 */
@RestController
public class ElectronicCasinoController {

	@Autowired
	private EstablishAGamePublisher establishAGamePublisher;
	
	@Autowired
	private JoinToAGamePublisher joinToAGamePublisher;
	
	@PostMapping("/joinToAGame")
	public String joinToAGame(@RequestBody JoinToAGameRequest joinToAGameRequest){
		joinToAGamePublisher.sentJoinToAGameMessage(joinToAGameRequest);
		return "Your request was sent succefully";
	}
	
	@PostMapping("/establishANewGame")
	public String establishNewGame(@RequestBody EstablishGameRequest establishGameRequest){
		establishAGamePublisher.sentStablishANewGameMessage(establishGameRequest);
		return "Your request was sent successfully.";
	}

}

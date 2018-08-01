package com.tradewind.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradewind.domain.constant.GameType;
import com.tradewind.domain.entity.Game;

public interface GameRepository extends JpaRepository<Game , String>{
	Game findByGameType(GameType gameType);	
}

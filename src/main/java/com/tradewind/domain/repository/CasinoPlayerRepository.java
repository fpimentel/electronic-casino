package com.tradewind.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tradewind.domain.entity.CasinoPlayer;


/**
 * @author fpimentelc
 * @version 1.0
 * This is the DAO class for casino player.
 */
public interface CasinoPlayerRepository extends JpaRepository<CasinoPlayer, String>{

}

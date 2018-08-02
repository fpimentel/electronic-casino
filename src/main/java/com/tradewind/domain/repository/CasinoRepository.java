package com.tradewind.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradewind.domain.entity.Casino;


/**
 * @author fpimentelc
 * @version 1.0
 * This is the DAO class for Casino.
 */
public interface CasinoRepository extends JpaRepository<Casino, String>{

}

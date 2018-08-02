package com.tradewind.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tradewind.domain.constant.Status;
import com.tradewind.domain.entity.Dealer;


/**
 * @author fpimentelc
 * @version 1.0
 * This is the DAO class for Dealer.
 */
public interface DealerRepository extends JpaRepository<Dealer, String>{
	List<Dealer> findByDealerStatus(Status status);
}

package com.tradewind.domain.repository;

import com.tradewind.domain.entity.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author fpimentelc
 * @see EventInfo 
 * @version 1.0
 * This class represent the DAO operation for EventType entity.
 * 
 */
public interface EventInfoRepository extends JpaRepository<EventInfo , String>{

}

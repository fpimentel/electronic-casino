package com.tradewinds.domain.repository;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tradewind.ElectronicCasinoApplication;
import com.tradewind.domain.entity.CasinoPlayer;

/**
 * Test class for the CasinoPlayerRepository CasinoPlayer class.
 *
 * @see CasinoPlayerRepository
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElectronicCasinoApplication.class)
@Transactional
public class CasinoPlayerRepositoryTest {
	
	//@Autowired
	//private CasinoPlayerRepository casinoPlayerRepository;

	List<CasinoPlayer> players = new ArrayList<>();
	
	@Before
    public void setup() {
		CasinoPlayer player1 = new CasinoPlayer();
		CasinoPlayer player2 = new CasinoPlayer();
    }

}

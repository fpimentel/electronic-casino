package com.tradewind.integration.test;

import com.google.common.io.Files;
import com.tradewind.domain.constant.GameType;
import com.tradewind.domain.dto.CasinoPlayerDTO;
import com.tradewind.domain.dto.EstablishGameRequest;
import com.tradewind.domain.dto.JoinToAGameRequest;
import com.tradewind.domain.dto.PlayerInfoDTO;
import com.tradewind.domain.entity.EventInfo;
import com.tradewind.domain.repository.EventInfoRepository;

import junit.framework.Assert;

import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;


/**
 * @author fpimentelc
 * This class will perform a integration test to confirm that the event sent in the app are working fine. 
 * This will load the real context of the app and will point to a local broker.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIntegrationTest {

	@Value("${spring.rabbitmq.port}")
	private String rabbitmqPort;

	@Value("${electronic_casino_exchange.name}")
	private String exchange;
	@Value("${electronic_casino_exchange.establish_new_game_routing_key.name}")
	private String establishNewGameRoutingKey;
	@Value("${electronic_casino_exchange.join_new_player.rounting_key.name}")
	private String joinToAGameRoutingKey;
	
	@Autowired
	private EventInfoRepository eventInfoRepository;
	
	public static final String QPID_CONFIG_LOCATION = "src/test/resources/qpid-config.json";
	public static final String APPLICATION_CONFIG_LOCATION = "src/test/resources/application.properties";

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@ClassRule
	public static final ExternalResource resource = new ExternalResource() {
		private Broker broker = new Broker();

		@Override
		protected void before() throws Throwable {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(APPLICATION_CONFIG_LOCATION)));
			String amqpPort = properties.getProperty("spring.rabbitmq.port");
			File tmpFolder = Files.createTempDir();
			String userDir = System.getProperty("user.dir").toString();
			File file = new File(userDir);
			String homePath = file.getAbsolutePath();
			BrokerOptions brokerOptions = new BrokerOptions();
			brokerOptions.setConfigProperty("qpid.work_dir", tmpFolder.getAbsolutePath());
			brokerOptions.setConfigProperty("qpid.amqp_port", amqpPort);
			brokerOptions.setConfigProperty("qpid.home_dir", homePath);
			brokerOptions.setInitialConfigurationLocation(homePath + "/" + QPID_CONFIG_LOCATION);
			broker.startup(brokerOptions);
		}

		@Override
		protected void after() {
			broker.shutdown();
		}
	};
	
	@Test
	public void whenEstablishNewGame_Then_DealerShouldHaveGameAndEventShouldBeSaveInDB() throws InterruptedException {
		EstablishGameRequest establishGameRequest = new EstablishGameRequest();
		establishGameRequest.setGameType(GameType.RPSGAME);
		List<EventInfo> beforeEstablishNewGameMessage = eventInfoRepository.findAll();
		Assert.assertTrue(beforeEstablishNewGameMessage.size() == 0);
		rabbitTemplate.convertAndSend(exchange, establishNewGameRoutingKey,establishGameRequest);
		Thread.sleep(3000);
		List<EventInfo> afterEstablishNewGameMessage = eventInfoRepository.findAll();
		Assert.assertTrue(afterEstablishNewGameMessage.size()==1);
	}
	
	@Test
	public void whenEstablishNewGame_Then_JoinAGameEventShouldBeSaveInDB() throws InterruptedException {
		JoinToAGameRequest joinToAGameRequest = new JoinToAGameRequest();
		joinToAGameRequest.setGameType(GameType.RPSGAME);
		CasinoPlayerDTO player = new CasinoPlayerDTO();
		player.setGameToPlay(GameType.RPSGAME);
		PlayerInfoDTO playeInfo = new PlayerInfoDTO();
		playeInfo.setAccountBalance(10000);
		player.setPlayeInfoDto(playeInfo);
		joinToAGameRequest.setPlayer(player);
		rabbitTemplate.convertAndSend(exchange, joinToAGameRoutingKey,joinToAGameRequest);
		Thread.sleep(3000);
		List<EventInfo> events = eventInfoRepository.findAll();
		Assert.assertTrue(events.get(0).getGameType() == GameType.RPSGAME);
	}
}

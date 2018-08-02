package com.tradewind.domain.dto;

import com.tradewind.domain.constant.GameType;

public class CasinoPlayerDTO implements IPlayer{
	private PlayerInfoDTO playeInfoDto;
	private GameType gameToPlay;
	
	public CasinoPlayerDTO() {
		super();
	}
	public CasinoPlayerDTO(PlayerInfoDTO playeInfoDto, GameType gameToPlay) {
		super();
		this.playeInfoDto = playeInfoDto;
		this.gameToPlay = gameToPlay;
	}
	public PlayerInfoDTO getPlayeInfoDto() {
		return playeInfoDto;
	}
	public void setPlayeInfoDto(PlayerInfoDTO playeInfoDto) {
		this.playeInfoDto = playeInfoDto;
	}
	public GameType getGameToPlay() {
		return gameToPlay;
	}
	public void setGameToPlay(GameType gameToPlay) {
		this.gameToPlay = gameToPlay;
	}
	@Override
	public int getAccontBalance() {
		return playeInfoDto.getAccountBalance();
	}
	@Override
	public void setAccountBalance(int accountBalance) {
		playeInfoDto.setAccountBalance(accountBalance);
	}
	@Override
	public String toString() {
		return "CasinoPlayerDTO [playeInfoDto=" + playeInfoDto + ", gameToPlay=" + gameToPlay + ", accountBalance="
				+ "]";
	}
}

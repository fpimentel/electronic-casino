package com.tradewind.domain.dto;

public class CasinoPlayerDTO implements IPlayer{
	private PlayerInfoDTO playeInfoDto;
	private IGame gameToPlay;
	
	public CasinoPlayerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CasinoPlayerDTO(PlayerInfoDTO playeInfoDto, IGame gameToPlay) {
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
	public IGame getGameToPlay() {
		return gameToPlay;
	}
	public void setGameToPlay(IGame gameToPlay) {
		this.gameToPlay = gameToPlay;
	}
}

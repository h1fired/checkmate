package com.checkmate.chess;

public class GameStatus {
	
	private STATUSES status;
	
	public GameStatus() {
		status = STATUSES.ACTIVE;
	}
	
	public enum STATUSES{
		ACTIVE, // активна гра
		BLACK_WIN, // виграла команда чорних
		WHITE_WIN, // виграла команда білих
		DRAW, // нічия
		STALEMATE // безвихідна ситуація
	}
	
	public void setStatus(STATUSES status) {
		this.status = status;
	}
	
	public STATUSES getStatus() {
		return this.status;
	}
	
	
	
	
}

package com.checkmate.chess;

public class Player {
	
	private boolean whiteSide;
	
	public Player(boolean white) {
		this.whiteSide = white;
	}
	
	
	
	//GETTERS & SETTERS
	public void setWhite(boolean white) {
		this.whiteSide = white;
	}
	
	public boolean isWhite() {
		return whiteSide;
	}
	
}

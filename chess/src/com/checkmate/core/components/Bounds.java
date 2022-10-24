package com.checkmate.core.components;

import com.checkmate.core.Input;

public class Bounds {
	
	private int x = 0;
	private int y = 0;
	private int maxX = 0;
	private int maxY = 0;
	
	public Bounds(int x, int y, int maxX, int maxY) {
		this.x = x;
		this.y = y;
		this.maxX = x + maxX;
		this.maxY = x + maxY;
	}
	
	public boolean isContain() {
		
		if(Input.getMousePosition().getX() >= x && Input.getMousePosition().getX() <= maxX && Input.getMousePosition().getY() >= y && Input.getMousePosition().getY() <= maxY) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	//GETTERS SETTERS
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getMaxX() {
		return maxX;
	}
	
	public int getMaxY() {
		return maxY;
	}
	
	public void setBounds(int x, int y, int maxX, int maxY) {
		this.x = x;
		this.y = y;
		this.maxX = x + maxX;
		this.maxY = y + maxY;
		
	}
	
}

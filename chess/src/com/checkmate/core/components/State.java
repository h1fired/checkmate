package com.checkmate.core.components;

import java.awt.Graphics2D;

import com.checkmate.core.GameContainer;

public abstract class State {
	
	
	public abstract void init(GameContainer gc, Graphics2D g);
	public abstract void update();
	public abstract void render();
	
	
}

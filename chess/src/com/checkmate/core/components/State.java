package com.checkmate.core.components;

import java.awt.Graphics2D;

public abstract class State {
	
	
	public abstract void init(Graphics2D g);
	public abstract void update();
	public abstract void render();
	
	
}

package com.checkmate.core.components.button;

import com.checkmate.core.Animation;
import com.checkmate.core.Input;
import com.checkmate.core.components.Bounds;
import com.checkmate.core.utils.Position;

public class CustomButton {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private String text;
	
	private boolean isVisible;
	private String tag;
	
	private Bounds bounds;
	
	public CustomButton() {
		this.tag = "";
		this.isVisible = true;
		this.x = 0;
		this.y = 0;
		this.width = 50;
		this.height = 50;
		
		initBounds();
		
	}
	
	private void initBounds() {
		this.bounds = new Bounds(x, y, width, height);
	}
	
	//CLICKABLE
	public boolean isClicked() {
		if(this.bounds.isContain() && Input.isMouseClicked()) {
			return true;
		}else {
			return false;
		}
	}
	
	//VISIBILITY
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	//TAG
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	
	//GETTERS SETTERS
	public Bounds getBounds() {
		return bounds;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		
		bounds.setBounds(x, y, width, height);
	}
	
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	
	public void setWidth(int width) {
		this.width = width;
		bounds.setBounds(x, y, width, height);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
		bounds.setBounds(x, y, width, height);;
	}
	
	public int getHeight() {
		return this.height;
	}
	
}

package com.checkmate.core.gfx;

import java.awt.image.BufferedImage;

import com.checkmate.core.utils.Position;
import com.checkmate.core.utils.ResourceLoader;

public class Sprite {
	
	private int x;
	private int y;
	private float scale;
	
	private BufferedImage image;
	private ResourceLoader fileLoader;
	private String path = "res/";
	private boolean isVisible;
	
	public Sprite(BufferedImage image) 
	{
		this.image = image;
		settings();
	}
	
	public Sprite(String filename) 
	{
		fileLoader = new ResourceLoader();
		image = fileLoader.loadImage(path + filename);
		settings();
	}
	
	
	
	private void settings() {
		isVisible = true;
		this.x = 0;
		this.y = 0;
		this.scale = 1f;
	}
	
	//GETTERS SETTERS
	public BufferedImage getImage() 
	{
		return image;
	}
	
	public void setImage(Sprite sprite) 
	{
		this.image = sprite.getImage();
	}
	
	public void setImage(BufferedImage image) 
	{
		this.image = image;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	
	public void setRenderScale(float scale) {
		this.scale = scale;
	}
	
	public float getRenderScale() {
		return this.scale;
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	
}

package com.checkmate.core.entity;

import java.awt.image.BufferedImage;

import com.checkmate.core.Animation;
import com.checkmate.core.components.Bounds;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.utils.Position;
import com.checkmate.core.utils.ResourceLoader;

public class EntityObject extends Animation{
	
	private int x;
	private int y;
	private int scaled_x;
	private int scaled_y;
	
	private float scale;
	private int anchorType;
	
	private boolean isBounded;
	private boolean isVisible;
	private boolean isAnimatedDrag;
	
	private BufferedImage image;
	private ResourceLoader fileLoader;
	private String path = "res/";
	private Bounds bounds;
	private AnchorPoint anchor;
	
	public EntityObject(BufferedImage image)
	{
		this.image = image;
		settings();
	}
	
	public EntityObject(String filename) 
	{
		fileLoader = new ResourceLoader();
		image = fileLoader.loadImage(path + filename);
		settings();
	}
	
	public EntityObject(Sprite sprite) 
	{
		this.image = sprite.getImage();
		settings();
	}
	
	private void settings() {
		this.isVisible = true;
		this.isBounded = true;
		this.isAnimatedDrag = true;
		this.x = 0;
		this.y = 0;
		this.scale = 1f;
		
		this.anchor = new AnchorPoint();
		this.anchorType = 4;
		initAnchorPoint();
		
		
		bounds = new Bounds(0, 0, (int)(image.getWidth() * this.scale), (int)(image.getHeight() * this.scale));
	}
	
	//BOUNDS
	public void setBounds(int x, int y, int maxX, int maxY) 
	{
		bounds.setBounds(x, y, maxX, maxY);
	}
	
	public Bounds getBounds() 
	{
		return bounds;
	}
	
	public void boundsDetect(boolean bound) 
	{
		this.isBounded = bound;
	}
	
	public boolean isContain() 
	{
		if(isBounded) {
			if(bounds.isContain()) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	//ANCHOR POINT
	public void setAnchorPoint(int anchorType) 
	{
		
		this.anchorType = anchorType;
		
	}
	
	public void initAnchorPoint() 
	{
		if(anchorType == 0) {
			this.scaled_x = (int)anchor.centered(image.getWidth(), image.getHeight(), scale).getX();
			this.scaled_y = (int)anchor.centered(image.getWidth(), image.getHeight(), scale).getY();
		}else if(anchorType == 1) {
			this.scaled_x = 0;
			this.scaled_y = (int)anchor.left(image.getWidth(), image.getHeight(), scale).getY();
		}else if(anchorType == 2) {
			this.scaled_x = (int)anchor.right(image.getWidth(), image.getHeight(), scale).getX();
			this.scaled_y = (int)anchor.right(image.getWidth(), image.getHeight(), scale).getY();
		}else if(anchorType == 3) {
			this.scaled_x = (int)anchor.top(image.getWidth(), image.getHeight(), scale).getX();
			this.scaled_y = (int)anchor.top(image.getWidth(), image.getHeight(), scale).getY();
		}else if(anchorType == 4) {
			this.scaled_x = 0;
			this.scaled_y = 0;
		}else if(anchorType == 5) {
			this.scaled_x = (int)anchor.topRight(image.getWidth(), image.getHeight(), scale).getX();
			this.scaled_y = 0;
		}else if(anchorType == 6) {
			this.scaled_x = (int)anchor.bottom(image.getWidth(), image.getHeight(), scale).getX();
			this.scaled_y = (int)anchor.bottom(image.getWidth(), image.getHeight(), scale).getY();
		}else if(anchorType == 7) {
			this.scaled_x = 0;
			this.scaled_y = (int)anchor.bottomLeft(image.getWidth(), image.getHeight(), scale).getY();
		}else if(anchorType == 8) {
			this.scaled_x = (int)anchor.bottomRight(image.getWidth(), image.getHeight(), scale).getX();
			this.scaled_y = (int)anchor.bottomRight(image.getWidth(), image.getHeight(), scale).getY();
		}
		
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
	
	//position
	public void setPosition(int x, int y) 
	{
		this.x = x;
		this.y = y;

		
		initAnchorPoint();
		
		bounds.setBounds(x, y, (int)(image.getWidth() * this.scale), (int)(image.getHeight() * this.scale));
	}
	
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	
	//scale
	public void setRenderScale(float scale) {
		this.scale = (float)Math.abs(scale);
		
		initAnchorPoint();
		
	}
	
	public float getRenderScale() {
		return this.scale;
	}
	
	
	//visibility
	public void setVisible(boolean visible) {
		isVisible = visible;
		if(visible) {
			isBounded = true;
		}else {
			isBounded = false;
		}
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	//scaled position
	public Position getScaledPos() {
		return new Position(scaled_x, scaled_y);
	}
	
	//animative
	public void enableDragAnimation(boolean animation) {
		isAnimatedDrag = animation;
	}
	
	public boolean isAnimatedDrag() {
		return isAnimatedDrag;
	}
	
	//OTHER
	public void printContain() 
	{
		System.out.println("CONTAIN = x: " + getBounds().getX() +  " || y: " + bounds.getY() + " || maxX: " + bounds.getMaxX() + " || maxY: " + bounds.getMaxY());
	}
	
}

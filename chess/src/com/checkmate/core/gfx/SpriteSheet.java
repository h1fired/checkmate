package com.checkmate.core.gfx;

import java.awt.image.BufferedImage;

import com.checkmate.core.GameContainer;

public class SpriteSheet {
	
	private BufferedImage image;
	private int spriteInWidth;
	private int spriteCount;
	private int scale = GameContainer.DEFAULT_TEX_SIZE;
	
	public SpriteSheet(BufferedImage image) 
	{
		this.image = image;
		this.spriteInWidth = image.getWidth() / scale;
		this.spriteCount = this.spriteInWidth * (image.getHeight() / scale);
	}
	
	public int getSpriteCount() 
	{
		return spriteCount;
	}
	
	public BufferedImage getSprite(int index) 
	{
		index = index % spriteCount;
		int x = index % spriteInWidth * scale;
		int y = index / spriteInWidth * scale;
		
		return image.getSubimage(x, y, scale, scale);
		
	}
}

package com.checkmate.core.gfx;

import java.awt.image.BufferedImage;

import com.checkmate.core.utils.ResourceLoader;

public class TextureAtlas {
	
	BufferedImage image;
	ResourceLoader fileLoader;
	
	public TextureAtlas(String imageName) {
		fileLoader = new ResourceLoader();
		image = fileLoader.loadImage(imageName);
	}
	
	public BufferedImage getAtlasImage() 
	{
		return image;
	}
	
	public BufferedImage cut(int x, int y, int w, int h) 
	{
		return image.getSubimage(x, y, w, h);
	}
}

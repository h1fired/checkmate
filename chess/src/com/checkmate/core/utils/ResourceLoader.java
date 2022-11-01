package com.checkmate.core.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	public final String PATH = "/com/checkmate/Resources/";
	
	public BufferedImage loadImage(String filename) 
	{
		BufferedImage image = null;
		String fullpath = PATH + filename;
		
		//System.out.println(PATH + filename);
		
		try {
			image = ImageIO.read(ResourceLoader.class.getResourceAsStream(fullpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
}

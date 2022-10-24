package com.checkmate.core.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	public final String PATH = "res/";
	
	public BufferedImage loadImage(String filename) 
	{
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(PATH + filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}

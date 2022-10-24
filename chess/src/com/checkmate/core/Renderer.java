package com.checkmate.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.checkmate.core.components.button.CustomButton;
import com.checkmate.core.entity.EntityObject;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.Text;

public class Renderer {
	
	private Graphics2D g;
	private BufferedImage image;
	
	public Renderer(Graphics2D g) {
		this.g = g;
	}
	
	public void drawImage(Sprite sprite) 
	{	
		g.drawImage(sprite.getImage(), (int)sprite.getPosition().getX(), (int)sprite.getPosition().getY(), (int)(sprite.getImage().getWidth() * sprite.getRenderScale()), (int)(sprite.getImage().getHeight() * sprite.getRenderScale()), null);
	}
	
	public void drawEntityObject(EntityObject object) 
	{	
		
		int x = (int)(object.getPosition().getX() - object.getScaledPos().getX());
		int y = (int)(object.getPosition().getY() - object.getScaledPos().getY());
		int width = (int)(object.getImage().getWidth() * object.getRenderScale());
		int height = (int)(object.getImage().getHeight() * object.getRenderScale());
		
		object.setBounds(x, y, width, height);
		
		g.drawImage(object.getImage(), x, y, width, height, null);
		
	}
	
	
	
	public void drawOval(int thickness, Color color, int x, int y, int width, int height) 
	{
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness));
		g.drawOval(x, y, width, height);
		g.setStroke(g.getStroke());
	}
	
	public void fillOval(Color color, int x, int y, int width, int height) 
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	public void drawString(Text text) 
	{
		g.setColor(text.getColor());
		
		if(text.getBold() && !text.getItalic()) {
			g.setFont(new Font(text.getFont(), Font.BOLD, text.getFontSize()));
		}else if(!text.getBold() && text.getItalic()) {
			g.setFont(new Font(text.getFont(), Font.ITALIC, text.getFontSize()));
		}else if(text.getBold() && text.getItalic()) {
			g.setFont(new Font(text.getFont(), Font.BOLD | Font.ITALIC, text.getFontSize()));
		}else {
			g.setFont(new Font(text.getFont(), Font.PLAIN, text.getFontSize()));
		}
		
		g.drawString(text.getText(), text.getPosition().getX(), text.getPosition().getY() + text.getHeight());
		//text.setWidth(g.getFontMetrics().stringWidth(text.getText()));
	}
	
	public void drawString(String text, String fontName, int size, Color color, int x, int y) 
	{
		g.setColor(color);
		g.setFont(new Font(fontName, Font.PLAIN, size));
		g.drawString(text, x, y);
	}
	
	public void drawButton(CustomButton button) {
		g.setColor(Color.red);
		g.fillRect((int)button.getPosition().getX(), (int)button.getPosition().getY(), button.getWidth(), button.getHeight());
	}
	
	
}

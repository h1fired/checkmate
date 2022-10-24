package com.checkmate.core.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import com.checkmate.core.utils.Position;

public class Text {
	
	private boolean isVisible;
	
	private String text;
	
	private int width;
	private int height;
	
	private int x;
	private int y;
	private int font_size;
	private String font;
	private Color color;
	
	private boolean bold;
	private boolean italic;
	
	private Font font_temp;
	private FontRenderContext frc;
	
	
	public Text() {
		text = "";
		this.text = "Text";
		
		settings();
	}
	
	public Text(String text) {
		this.text = text;
		
		settings();
	}
	
	private void settings() {
		this.isVisible = true;
		this.bold = false;
		this.italic = false;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.font_size = 34;
		this.color = Color.WHITE;
		this.font = "Arial";
		
		frc = new FontRenderContext(new AffineTransform(), true, true);
		metrics();
	}
	
	//FONT METRICS
	private void metrics() {
		if(bold && !italic) {
			font_temp = new Font(this.font, Font.BOLD, this.font_size);
		}else if(!bold && italic) {
			font_temp = new Font(this.font, Font.ITALIC, this.font_size);
		}else if(bold && italic) {
			font_temp = new Font(this.font, Font.BOLD | Font.ITALIC, this.font_size);
		}else {
			font_temp = new Font(this.font, Font.PLAIN, this.font_size);
		}
		
		this.width = (int)(font_temp.getStringBounds(text, frc).getWidth());
		this.height = (int)(font_temp.getStringBounds(text, frc).getHeight());
	}
	
	//VISIBILITY
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	
	//GETTERS & SETTERS
	
	//position
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	
	//font size
	public void setFontSize(int font_size) {
		this.font_size = font_size;
		metrics();
	}
	
	public int getFontSize() {
		return this.font_size;
	}
	
	//color
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setColor(String hex) {
		this.color = color.decode(hex);
	}
	
	public void setColor(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	//font name
	public void setFont(String font) {
		this.font = font;
		metrics();
	}
	
	public String getFont() {
		return this.font;
	}
	
	//text
	public void setText(String text) {
		this.text = text;
		metrics();
	}
	
	public String getText() {
		return this.text;
	}
	
	//text width height
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	//font bold
	public void setBold(boolean bold) {
		this.bold = bold;
		metrics();
	}
	
	public void setItalic(boolean italic) {
		this.italic = italic;
		metrics();
	}
	
	public boolean getBold() {
		return bold;
	}
	
	public boolean getItalic() {
		return italic;
	}
	
	//set all parameters
	public void setParameters(String text, String font, int size, Color color, boolean bold, boolean italic) {
		this.text = text;
		this.font = font;
		this.font_size = size;
		this.color = color;
		this.bold = bold;
		this.italic = italic;
		metrics();
	}
	
	
}

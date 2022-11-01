package com.checkmate.core.components.button;

import java.awt.Color;

import com.checkmate.core.Input;
import com.checkmate.core.Renderer;
import com.checkmate.core.components.Bounds;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.Text;
import com.checkmate.core.utils.Position;

public class CustomButton {
	
	//default
	private int x;
	private int y;
	private int width;
	private int height;
	private Bounds bounds;
	private Text caption;
	
	//booleans
	private boolean clicked;
	private boolean visible;
	private boolean enabled;
	
	//icon
	private Sprite unclick;
	private Sprite click;
	
	public CustomButton(int x, int y, int width, int height, String caption) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.bounds = new Bounds(x, y, width, height);
		this.caption = new Text(caption);
		this.caption.setFontSize(19);
		this.caption.setPosition(x + (width / 2) - (this.caption.getWidth() / 2), y + (height / 2) - (this.caption.getHeight() / 2 + 3));
		
		this.clicked = false;
		this.visible = true;
		this.enabled = true;
		
	}
	
	public CustomButton(int x, int y, float scale, Sprite unclick, Sprite click) {
		this.x = x;
		this.y = y;
		this.unclick = unclick;
		this.click = click;
		this.unclick.setPosition(x, y);
		this.click.setPosition(x, y);
		this.click.setRenderScale(scale);
		this.unclick.setRenderScale(scale);
		
		this.bounds = new Bounds(x, y, (int)(click.getImage().getWidth() * click.getRenderScale()), (int)(click.getImage().getHeight() * click.getRenderScale()));
		
		this.clicked = false;
		this.visible = true;
		this.enabled = true;
	}
	
	//LOGIC
	public void render(Renderer rs) {
		
		
		
		if(click == null) {
			rs.drawButton(this);
		}else {
			if(Input.isMousePressed() && bounds.isContain() && enabled) {
				rs.drawImage(this.click);
			}else
				rs.drawImage(this.unclick);
		}
	}
	
	public void update() {
		
		//second
		
		
		
		
		if(enabled && visible && bounds.isContain()) {
			
			if(Input.isMouseReleased()) {
				clicked = true;
				
				
			}
			if(click == null) {
				
				if(Input.isMousePressed()) {
					caption.setColor(Color.black);
				}
				
				if(!Input.isMousePressed()) {
					caption.setColor(Color.white);
				}
				
			}
			
		}
		
		if(!Input.isMouseReleased()) {
			clicked = false;
		}
		
		if(clicked) {
			System.out.println("CLICKED!");
		}
	}
	
	
	//GETTERS & SETTERS
	//position
	public void setPosition(int x, int y) 
	{
		this.x = x;
		this.y = y;
		
		
		if(click == null) {
			bounds.setBounds(x, y, this.width, this.height);
			this.caption.setPosition(x + (this.width / 2) - (this.caption.getWidth() / 2), y + (this.height / 2) - (this.caption.getHeight() / 2 + 3));
		}else {
			bounds.setBounds(x, y, (int)(click.getImage().getWidth() * click.getRenderScale()), (int)(click.getImage().getHeight() * click.getRenderScale()));
			click.setPosition(x, y);
			unclick.setPosition(x, y);
		}
	}
	
	public Position getPosition() {
		return new Position(this.x, this.y);
	}
	
	//caption
	public void setCaption(String caption) 
	{
		this.caption.setText(caption);
		this.setPosition(x, y);
	}
	
	public Text getCaption() {
		return this.caption;
	}
	
	//size
	public void setSize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		bounds.setBounds(x, y, this.width, this.height);
	}
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	public Sprite getSprite() {
		return click;
	}
	
	//visibility
	public void setVisible(boolean visible) {
		this.visible = visible;
		if(visible) {
			enabled = true;
		}else {
			enabled = false;
		}
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	//enabled
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(enabled) {
			enabled = true;
		}else {
			enabled = false;
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	//clicked
	public boolean isClicked() {
		return clicked;
	}
}

package com.checkmate.core.components;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.checkmate.core.Input;
import com.checkmate.core.Renderer;
import com.checkmate.core.components.button.CustomButton;
import com.checkmate.core.entity.EntityObject;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.Text;

public class ObjectManager {
	
	private Renderer rs;
	
	private ArrayList<EntityObject> entitys = new ArrayList<EntityObject>();
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private ArrayList<Text> strings = new ArrayList<Text>();
	private ArrayList<CustomButton> buttons = new ArrayList<CustomButton>();
	
	public ObjectManager(Graphics2D g) {
		rs = new Renderer(g);
	}
	
	
	public void add(EntityObject e) 
	{
		entitys.add(e);
	}
	
	public void add(Sprite e) 
	{
		sprites.add(e);
	}
	
	public void add(Text e) 
	{
		strings.add(e);
	}
	
	public void add(CustomButton e) 
	{
		buttons.add(e);
	}
	
	public void update() {
		if(entitys.size() > 0) {
			for(int i = 0; i < entitys.size(); i++) {
				entitys.get(i).DragAnimation(entitys.get(i));
			}
		}
		
		
	}
	
	public void render() 
	{
		if(sprites.size() > 0) {
			for(int i = 0; i < sprites.size(); i++) {
				if(sprites.get(i).isVisible()) {
					rs.drawImage(sprites.get(i));
				}
			}
		}
		
		if(entitys.size() > 0) {
			for(int i = 0; i < entitys.size(); i++) {
				if(entitys.get(i).isVisible()) {
					rs.drawEntityObject(entitys.get(i));
				}
			}
		}
		
		if(entitys.size() > 0) {
			for(int i = 0; i < strings.size(); i++) {
				if(strings.get(i).isVisible()) {
					rs.drawString(strings.get(i));
				}
				
			}
		}
		
		if(buttons.size() > 0) {
			for(int i = 0; i < buttons.size(); i++) {
				if(buttons.get(i).isVisible()) {
					rs.drawButton(buttons.get(i));
				}
				
			}
		}
	}
	
}

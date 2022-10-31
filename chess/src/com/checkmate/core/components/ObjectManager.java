package com.checkmate.core.components;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.checkmate.chess.Board;
import com.checkmate.core.Renderer;
import com.checkmate.core.Sound;
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
	private ArrayList<Sound> sounds = new ArrayList<Sound>();
	
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

	public void add(Sound e) 
	{
		sounds.add(e);
	}
	
	public void removeEntitys() {
		entitys = null;
		entitys = new ArrayList<EntityObject>();
	}
	
	public void update() {
		if(entitys.size() > 0) {
			for(int i = 0; i < entitys.size(); i++) {
				entitys.get(i).DragAnimation(entitys.get(i));
			}
		}
		
		if(buttons.size() > 0) {
			for(int i = 0; i < buttons.size(); i++) {
				buttons.get(i).update();
			}
		}
		if(sounds.size() > 0) {
			for(int i = 0; i < sounds.size(); i++) {
				sounds.get(i).volume(Sound.VOLUME);
			}
		}
		
	}
	
	public void render() 
	{
		if(entitys.size() > 0) {
			for(int i = 0; i < entitys.size(); i++) {
				if(entitys.get(i).isVisible()) {
					rs.drawEntityObject(entitys.get(i));
				}
			}
			if(entitys.get(Board.prevFigureIndex).isVisible()) {
				rs.drawEntityObject(entitys.get(Board.prevFigureIndex));
			}
		}
		
		if(sprites.size() > 0) {
			for(int i = 0; i < sprites.size(); i++) {
				if(sprites.get(i).isVisible()) {
					rs.drawImage(sprites.get(i));
				}
			}
		}
		
		if(buttons.size() > 0) {
			for(int i = 0; i < buttons.size(); i++) {
				if(buttons.get(i).isVisible()) {
					buttons.get(i).render(rs);
				}
				
			}
		}
		
		if(strings.size() > 0) {
			for(int i = 0; i < strings.size(); i++) {
				if(strings.get(i).isVisible()) {
					rs.drawString(strings.get(i));
				}
				
			}
		}
		
		
	}
	
}

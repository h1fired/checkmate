package com.checkmate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.checkmate.core.Input;
import com.checkmate.core.components.ObjectManager;
import com.checkmate.core.components.State;
import com.checkmate.core.components.button.CustomButton;
import com.checkmate.core.entity.EntityObject;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.SpriteSheet;
import com.checkmate.core.gfx.Text;
import com.checkmate.core.gfx.TextureAtlas;

public class Menu extends State{
	
	private ObjectManager manager;
	
	//temp
	private TextureAtlas atlas;
	private SpriteSheet sheet;
	private Sprite sprite;
	private EntityObject entity;
	private Text text;
	private CustomButton button;
	
	private float delta_x = 0;
	
	
	
	@Override
	public void init(Graphics2D g) {
		manager = new ObjectManager(g);
		
		text = new Text("TEST");
		text.setParameters("Names", "Arial", 32, Color.WHITE, false, false);
		text.setPosition(100, 0);
		
		
		manager.add(text);
		
		atlas = new TextureAtlas("chess_texatlas.png");
		sheet = new SpriteSheet(atlas.getAtlasImage());
		sprite = new Sprite(sheet.getSprite(0));
		sprite.setPosition(100, 100);
		manager.add(sprite);
		
		entity = new EntityObject(sprite);
		entity.enableDragAnimation(true);
		entity.setPosition(300, 300);
		manager.add(entity);
		
		button = new CustomButton();
		button.setPosition(200, 600);
		
		manager.add(button);
		
		
		
	}
	
	//
	
	//SETTINGS STATE BUTTON
	public void setSettingsState(CustomButton button) {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void update() {
		manager.update();
		
		if(button.isClicked()) {
			System.out.println("CLICKED!");
		}
		delta_x += 0.5f;
		button.setPosition((int)delta_x, 600);
		
	}

	@Override
	public void render() {
		manager.render();
		
		
	}
	
}

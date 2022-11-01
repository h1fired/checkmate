package com.checkmate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.checkmate.core.GameContainer;
import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.Settings.STATES;
import com.checkmate.core.components.ObjectManager;
import com.checkmate.core.components.State;
import com.checkmate.core.components.button.CustomButton;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.Text;
import com.checkmate.core.utils.Translate;

public class Menu extends State{
	
	private ObjectManager manager;
	private GameContainer gc;
	private Renderer rs;
	
	//buttons
	private CustomButton button_startGame;
	private CustomButton button_exit;
	private CustomButton button_settings;
	
	private Sprite button_click[];
	private Sprite button_unclick[];
	private Text button_texts[];
	
	//background
	private Sprite bgTex;
	
	
	
	
	@Override
	public void init(GameContainer gc, Graphics2D g) {
		this.gc = gc;
		manager = new ObjectManager(g);
		this.rs = new Renderer(g);
		
		//buttons
		button_click = new Sprite[3];
		button_unclick = new Sprite[3];
		for(int i = 0; i < 3; i++) {
			button_click[i] = new Sprite("images/button_click.png");
			button_unclick[i] = new Sprite("images/button_unclick.png");
		}
		
		
		
		//start part
		button_startGame = new CustomButton(100, 10, 3f, button_click[0], button_unclick[0]);
		button_startGame.setPosition(Settings.WIDTH / 2 - (int)(button_startGame.getSprite().getImage().getWidth() * button_startGame.getSprite().getRenderScale() / 2) , 200);
		
		manager.add(button_startGame);
		//exit
		button_exit = new CustomButton(100, 20, 3f, button_click[1], button_unclick[1]);
		button_exit.setPosition(Settings.WIDTH / 2 - (int)(button_exit.getSprite().getImage().getWidth() * button_exit.getSprite().getRenderScale() / 2), 200 + 200);
		
		manager.add(button_exit);
		//settings
		button_settings = new CustomButton(100, 30, 3f, button_click[2], button_unclick[2]);
		button_settings.setPosition(Settings.WIDTH / 2 - (int)(button_settings.getSprite().getImage().getWidth() * button_settings.getSprite().getRenderScale() / 2), 200 + 100);
		
		manager.add(button_settings);
		
		//texts
		button_texts = new Text[3];
		
		button_texts[0] = new Text(Translate.UKR.get("start_part"));
		button_texts[0].setFont("Consolas");
		button_texts[0].setColor(Color.BLACK);
		button_texts[0].setFontSize(25);
		button_texts[0].setPosition(Settings.WIDTH / 2 - button_texts[0].getWidth() / 2, 215);
		manager.add(button_texts[0]);
		
		button_texts[1] = new Text(Translate.UKR.get("settings"));
		button_texts[1].setFont("Consolas");
		button_texts[1].setColor(Color.BLACK);
		button_texts[1].setFontSize(25);
		button_texts[1].setPosition(Settings.WIDTH / 2 - button_texts[1].getWidth() / 2, 315);
		manager.add(button_texts[1]);
		
		button_texts[2] = new Text(Translate.UKR.get("exit"));
		button_texts[2].setFont("Consolas");
		button_texts[2].setColor(Color.BLACK);
		button_texts[2].setFontSize(25);
		button_texts[2].setPosition(Settings.WIDTH / 2 - button_texts[2].getWidth() / 2, 415);
		manager.add(button_texts[2]);
		
		//bg
		bgTex = new Sprite("images/menu_bg.png");
		bgTex.setRenderScale(1.5f);
		
		
		
		
	}
	
	private void changeLang() {
		//Language Choose
		if(Translate.LANGUAGE == 1) {
			button_texts[0].setText(Translate.UKR.get("start_part"));
			button_texts[2].setText(Translate.UKR.get("exit"));
			button_texts[1].setText(Translate.UKR.get("settings"));
		}else if(Translate.LANGUAGE == 2) {
			button_texts[0].setText(Translate.ENG.get("start_part"));
			button_texts[2].setText(Translate.ENG.get("exit"));
			button_texts[1].setText(Translate.ENG.get("settings"));
		}
		button_texts[0].setPosition(Settings.WIDTH / 2 - button_texts[0].getWidth() / 2, 215);
		button_texts[1].setPosition(Settings.WIDTH / 2 - button_texts[1].getWidth() / 2, 315);
		button_texts[2].setPosition(Settings.WIDTH / 2 - button_texts[2].getWidth() / 2, 415);
	}
	
	private void bgDraw() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 6; j++) {
				rs.drawImageTile(bgTex, (int)(bgTex.getImage().getWidth()* bgTex.getRenderScale() * i), (int)(bgTex.getImage().getHeight() * bgTex.getRenderScale() * j), (int)(bgTex.getImage().getWidth() * bgTex.getRenderScale()), (int)(bgTex.getImage().getHeight() * bgTex.getRenderScale()));
			}
		}
	}

	@Override
	public void update() {
		manager.update();
		
		if(button_startGame.isClicked()) {
			gc.game.getBoard().reset();
			Settings.CURRENT_STATE = STATES.GAME;
		}
		if(button_exit.isClicked()) {
			gc.destroy();
		}
		if(button_settings.isClicked()) {
			Settings.CURRENT_STATE = STATES.SETTINGS;
		}
		
		changeLang();
	}

	@Override
	public void render() {
		bgDraw();
		manager.render();
	}
	
}

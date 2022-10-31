package com.checkmate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.checkmate.core.GameContainer;
import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.Settings.STATES;
import com.checkmate.core.Sound;
import com.checkmate.core.components.ObjectManager;
import com.checkmate.core.components.Slider;
import com.checkmate.core.components.State;
import com.checkmate.core.components.button.CustomButton;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.Text;
import com.checkmate.core.utils.Translate;

public class SettingsMenu extends State {

	private ObjectManager manager;
	private Renderer rs;
	

	
	//menu back
	private Sprite button_click;
	private Sprite button_unclick;
	private Text text_menu;
	
	//Lang Choose
	private Text langChoose;
	private Text volumeText;
	private CustomButton langUkr;
	private CustomButton langEng;
	private CustomButton menuBack;
	private Sprite ukrainianLang;
	private Sprite englishLang;
	
	//Volume Control
	private Slider volumeSlider;
	private Sound bgSound;
	
	//bg
	private Sprite bgTex;
	
	@Override
	public void init(GameContainer gc, Graphics2D g) {
		manager = new ObjectManager(g);
		rs = new Renderer(g);
		
		ukrainianLang = new Sprite("images/flag_ukr.png");
		englishLang = new Sprite("images/flag_eng.png");
		
		//menu back
		button_click = new Sprite("images/button_click.png");
		button_unclick = new Sprite("images/button_unclick.png");
		
		//lang choose
		langChoose = new Text(Translate.UKR.get("lang_choose"));
		langChoose.setFont("Consolas");
		langChoose.setFontSize(25);
		langChoose.setPosition(Settings.WIDTH / 2 - langChoose.getWidth() / 2, 215);
		manager.add(langChoose);
		langUkr = new CustomButton(100, 100, 3f, ukrainianLang, ukrainianLang);
		langEng = new CustomButton(100, 200, 3f, englishLang, englishLang);
		langUkr.setPosition(Settings.WIDTH / 2 - (int)(langUkr.getSprite().getImage().getWidth() * langUkr.getSprite().getRenderScale() / 2 - 150 ) - 75, 280);
		langEng.setPosition(Settings.WIDTH / 2 - (int)(langUkr.getSprite().getImage().getWidth() * langUkr.getSprite().getRenderScale() / 2) - 75, 280);
		manager.add(langUkr);
		manager.add(langEng);
		
		
		//Volume Control
		volumeText = new Text(Translate.UKR.get("volume"));
		volumeText.setFont("Consolas");
		volumeText.setFontSize(25);
		volumeText.setPosition(Settings.WIDTH / 2 - volumeText.getWidth() / 2, 370);
		manager.add(volumeText);
		
		volumeSlider = new Slider(Settings.WIDTH / 2 - 125, 440, 250, 3, 20);
		
		//Menu Back
		menuBack = new CustomButton(100, 10, 3f, button_click, button_unclick);
		menuBack.setPosition(Settings.WIDTH / 2 - (int)(menuBack.getSprite().getImage().getWidth() * menuBack.getSprite().getRenderScale() / 2) , 500);
		manager.add(menuBack);
		text_menu = new Text(Translate.UKR.get("menu"));
		text_menu.setFont("Consolas");
		text_menu.setColor(Color.BLACK);
		text_menu.setFontSize(25);
		text_menu.setPosition(Settings.WIDTH / 2 - text_menu.getWidth() / 2, 515);
		manager.add(text_menu);
		
		bgSound = new Sound();
		bgSound.setFile("bg_music1.wav");
		manager.add(bgSound);
		bgSound.play();
		
		//bg
		bgTex = new Sprite("images/wood_bg.png");
		bgTex.setRenderScale(1.5f);
		
		
		
		//last
		//changeLang();
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
		
		if(langUkr.isClicked()) {
			Translate.LANGUAGE = 1;
			changeLang();
		}else if(langEng.isClicked()) {
			Translate.LANGUAGE = 2;
			changeLang();
		}
		
		if(menuBack.isClicked()) {
			Settings.CURRENT_STATE = STATES.MENU;
		}
		
		volumeSlider.update();
		Sound.VOLUME = volumeSlider.getValue();
		
		
		
		
	}

	@Override
	public void render() {
		bgDraw();
		manager.render();
		volumeSlider.render(rs);
		
	}
	
	private void changeLang() {
		//Language Choose
		if(Translate.LANGUAGE == 1) {
			volumeText.setText(Translate.UKR.get("volume"));
			langChoose.setText(Translate.UKR.get("lang_choose"));
			text_menu.setText(Translate.UKR.get("menu"));
		}else if(Translate.LANGUAGE == 2) {
			volumeText.setText(Translate.ENG.get("volume"));
			langChoose.setText(Translate.ENG.get("lang_choose"));
			text_menu.setText(Translate.ENG.get("menu"));
		}
		langChoose.setPosition(Settings.WIDTH / 2 - langChoose.getWidth() / 2, 215);
		volumeText.setPosition(Settings.WIDTH / 2 - volumeText.getWidth() / 2, 370);
	}

}

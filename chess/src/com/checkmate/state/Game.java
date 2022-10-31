package com.checkmate.state;

import java.awt.Color;
import java.awt.Graphics2D;

import com.checkmate.chess.Board;
import com.checkmate.chess.Timer;
import com.checkmate.core.GameContainer;
import com.checkmate.core.Input;
import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.Settings.STATES;
import com.checkmate.core.components.ObjectManager;
import com.checkmate.core.components.State;
import com.checkmate.core.components.button.CustomButton;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.Text;
import com.checkmate.core.utils.Translate;

public class Game extends State{
	
	//STATIC VARS
	
	private static int mapSize = 8;
	
	
	//OBJECT MANAGER & RENDERER
	private ObjectManager manager;
	private Renderer rs;
	
	//PART OBJECTS
	private Board board;
	
	//WIN
	private Text winText;
	
	//Timer
	private Timer timer;
	private Sprite bgTimer;
	
	//Pause
	private Sprite pauseMenu;
	private CustomButton button_pause;
	private Sprite pauseIcon;
	private Sprite pauseIcon_click;
	private boolean pauseShow;
	private CustomButton button_menu;
	private CustomButton button_continue;
	private boolean pauseActive;
	
	//check mate
	private Sprite checkMate[];
	private Text checkmateCap;
	
	//Background tex
	private Sprite bgTex;
	
	
	@Override
	public void init(GameContainer gc, Graphics2D g) 
	{
		
		//renderer
		rs = new Renderer(g);
		
		//object manager & renderer initialize
		manager = new ObjectManager(g);
		
		board = new Board(rs, 360, 100, 0.5f);
		board.setPosition(360, 100);

		for(int i = 0; i < 32; i++) {
			manager.add(board.getFigures(i));
		}
		
		
		
		//timer
		bgTimer = new Sprite("/images/timer_fill.png");
		bgTimer.setRenderScale(2.8f);
		bgTimer.setPosition(Settings.WIDTH - (int)(bgTimer.getImage().getWidth() * bgTimer.getRenderScale()) - 80, 10);
		timer = new Timer();
		timer.setPosition(Settings.WIDTH - timer.getText().getWidth() - 100, 32);
		timer.start();
		
		//pause
		pauseMenu = new Sprite("images/pause_panel.png");
		pauseMenu.setRenderScale(4f);
		pauseMenu.setPosition(Settings.WIDTH / 2 - (int)(pauseMenu.getImage().getWidth() * pauseMenu.getRenderScale()) / 2 - 20, Settings.HEIGHT / 2 - (int)(pauseMenu.getImage().getHeight() * pauseMenu.getRenderScale()) / 2 - 10);
		manager.add(pauseMenu);
		pauseMenu.setVisible(false);
		pauseIcon = new Sprite("images/pause_icon.png");
		pauseIcon_click = new Sprite("images/pause_icon_click.png");
		button_pause = new CustomButton(500, 500, 2.8f, pauseIcon, pauseIcon_click);
		button_pause.setPosition(Settings.WIDTH - (int)(button_pause.getSprite().getImage().getWidth() * button_pause.getSprite().getRenderScale()), 10);
		manager.add(button_pause);
		pauseShow = false;
		pauseActive = true;
		
		//pause go menu
		button_menu = new CustomButton(100, 500, 350, 50, Translate.UKR.get("go_menu"));
		button_menu.setPosition(Settings.WIDTH / 2 - button_menu.getWidth() / 2 - 20, Settings.HEIGHT / 2 - button_menu.getHeight() + 45);
		manager.add(button_menu);
		button_menu.setVisible(false);
		
		//pause go to game
		button_continue = new CustomButton(100, 700, 350, 50, Translate.UKR.get("go_continue"));
		button_continue.setPosition(Settings.WIDTH / 2 - button_continue.getWidth() / 2-20, Settings.HEIGHT / 2 - button_menu.getHeight() - 20);
		manager.add(button_continue);
		button_continue.setVisible(false);
		
		//check mate
		checkMate = new Sprite[2];
		checkMate[0] = new Sprite("images/check_mate_no.png");
		checkMate[1] = new Sprite("images/check_mate_yes.png");
		for(int i = 0; i < 2; i++) {
			checkMate[i].setRenderScale(2.8f);
			checkMate[i].setPosition(950, 575);
		}
		
		checkmateCap = new Text(Translate.UKR.get("checkmate"));
		checkmateCap.setFont("Consolas");
		checkmateCap.setColor(Color.white);
		checkmateCap.setFontSize(18);
		checkmateCap.setPosition(985 - checkmateCap.getWidth() / 2, 550);
		manager.add(checkmateCap);
		
		winText = new Text("");
		winText.setFont("Consolas");
		winText.setColor(Color.white);
		winText.setFontSize(25);
		winText.setPosition(10 - winText.getWidth() / 2, 0);
		manager.add(winText);
		
		//textures
		bgTex = new Sprite("images/wood_bg.png");
		bgTex.setRenderScale(2f);
		
		
	}
	
	@Override
	public void update() 
	{
		manager.update();
		
		if(board != null) {
			board.update();
		}
		
		
		timer.update();
		
		if(board != null) {
			if(board.getWinSide() == 1) {
				board.setActive(false);
				
				if(Translate.LANGUAGE == 1) {
					winText.setText("Виграла команда чорних!");
				}else if(Translate.LANGUAGE == 2) {
					winText.setText("BLACK TEAM WIN!");
				}
			}else if(board.getWinSide() == 2) {
				board.setActive(false);
				if(Translate.LANGUAGE == 1) {
					winText.setText("Виграла команда білих!");
				}else if(Translate.LANGUAGE == 2) {
					winText.setText("WHITE TEAM WIN!");
				}
				
			}
		}
		
		if(Input.isMouseClicked()) {
			if(board.getWinSide() == 1 || board.getWinSide() == 2) {
				Settings.CURRENT_STATE = STATES.MENU;
			}
		}
		
		if(button_pause.isClicked()) {
			pauseShow = true;
		}
		if(button_continue.isClicked()) {
			pauseShow = false;
		}
		if(button_menu.isClicked()) {
			Settings.CURRENT_STATE = STATES.MENU;
		}
		
		
		changeLang();
	}

	@Override
	public void render() 
	{
		bgDraw();
		if(board != null) {
			board.render();
		}
		initCurrentPlayerShow();
		
		
		
		manager.render();
		
		pausePanel();
		//time
		rs.drawImage(bgTimer);
		timer.render(rs);
		checkMatePanel();
		
		
	}
	
	private void changeLang() {
		//Language Choose
		if(Translate.LANGUAGE == 1) {
			checkmateCap.setText(Translate.UKR.get("checkmate"));
		}else if(Translate.LANGUAGE == 2) {
			checkmateCap.setText(Translate.ENG.get("checkmate"));
		}
		checkmateCap.setPosition(985 - checkmateCap.getWidth() / 2, 550);
	}
	
	private void bgDraw() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 6; j++) {
				rs.drawImageTile(bgTex, (int)(bgTex.getImage().getWidth()* bgTex.getRenderScale() * i), (int)(bgTex.getImage().getHeight() * bgTex.getRenderScale() * j), (int)(bgTex.getImage().getWidth() * bgTex.getRenderScale()), (int)(bgTex.getImage().getHeight() * bgTex.getRenderScale()));
			}
		}
	}
	
	private void initCurrentPlayerShow() {
		if(board != null) {
			if(board.getPlayer()) {
				rs.fillRect(Color.green, 915, 540, 15, 100);
			}else {
				rs.fillRect(Color.green, 915, 72, 15, 100);
			}
		}
		
		rs.drawRect(2, Color.black, 915, 72, 15, 100);
		rs.drawRect(2, Color.black, 915, 540, 15, 100);
		
	}
	
	private void pausePanel() {
		if(pauseShow) {
			if(pauseActive) {
				pauseMenu.setVisible(true);
				button_pause.setEnabled(false);
				button_menu.setVisible(true);
				button_continue.setVisible(true);
				if(board != null) {
					board.setActive(false);
				}
				timer.stop();
				pauseActive = false;
			}
		}else {
			if(!pauseActive) {
				pauseMenu.setVisible(false);
				button_pause.setEnabled(true);
				button_menu.setVisible(false);
				button_continue.setVisible(false);
				if(board != null) {
					board.setActive(true);
				}
				timer.start();
				pauseActive = true;
			}
		}
	}
	
	private void checkMatePanel() {
		if(board != null) {
			if(board.isCheckmate()) {
				rs.drawImage(checkMate[1]);
			}else {
				rs.drawImage(checkMate[0]);
			}
		}
	}
	
	//GETTERS & SETTERS
	public static int getMapSize() {
		return mapSize;
	}
	
	
	
}

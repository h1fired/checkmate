package com.checkmate.core;

import java.awt.Graphics2D;

import com.checkmate.core.utils.Time;
import com.checkmate.core.utils.Translate;
import com.checkmate.debug.Statistics;
import com.checkmate.state.Game;
import com.checkmate.state.Menu;
import com.checkmate.state.SettingsMenu;

public class GameContainer implements Runnable {
	
	//DEBUG VARS
	public static int curFPS = 0;
	public static int curUPD = 0;
	public static int curUPDL = 0;
	

	//GAMECONTAINER VARS
	private String TITLE = "Checkmate";
	private int WIDTH = Settings.WIDTH;
	private int HEIGHT = Settings.HEIGHT;
	private float UPDATE_RATE = Settings.FPS;
	private int NUM_BUFFERS = 3;
	
	public static int DEFAULT_TEX_SIZE = Settings.SINGLE_TEX_SCALE;
	
	private Thread thread;
	private Display window;
	private Input input;
	
	
	private boolean isRunning;
	
	//STATS
	private Statistics stats;
	
	//STATE
	public static Menu menu;
	public static Game game;
	public static SettingsMenu settings;
	
	
	
	public GameContainer() 
	{
		isRunning = false;
		window = new Display(WIDTH, HEIGHT, TITLE, NUM_BUFFERS);
		input = new Input();
		window.setMouseListener(input);//
		Translate.setValues();
		stats = new Statistics();
		
		
		//STATES
		menu = new Menu();
		menu.init(this, window.getGraphics());
		game = new Game();
		game.init(this, window.getGraphics());
		settings = new SettingsMenu();
		settings.init(this, window.getGraphics());
		
		
		
		
		
	}
	
	public void start() 
	{
		if(isRunning) {
			return;
		}
		
		isRunning = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() 
	{
		if(!isRunning) {
			return;
		}
		
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void run() 
	{
		long lastTime = Time.get();
		float delta = 0;
		long count = 0;
		
		int fps = 0;
		int upd = 0;
		int updl = 0;
		
		while(isRunning) {
			long now = Time.get();
			long elapsedTime = now - lastTime;
			lastTime = now;
			
			count += elapsedTime;
			
			delta += (elapsedTime / (Time.SECOND / UPDATE_RATE));
			
			boolean render = false;
			while(delta >= 1) {
				delta--;
				
				update();
				upd++;

				if(render) {
					updl++;
				}else {
					render = true;
				}
				
			}
			
			if(count >= Time.SECOND) {
				count = 0;
				curFPS = fps;
				curUPD = upd;
				curUPDL = updl;
				
				fps = 0;
				upd = 0;
				updl = 0;
				
				
			}
			
			
			if(render) {
				render();
				fps++;
			}else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	public void update() {

		if(Settings.CURRENT_STATE == Settings.STATES.MENU) {
			
			menu.update();
		}else if(Settings.CURRENT_STATE == Settings.STATES.GAME) {
			game.update();
		}else if(Settings.CURRENT_STATE == Settings.STATES.SETTINGS) {
			settings.update();
		}
		
		for(int i = 0; i < 2; i++) {
			Input.clearMouseClick();
		}
	}
	
	public void render() {
		window.clear();
		
		if(Settings.CURRENT_STATE == Settings.STATES.MENU) {
			menu.render();
		}else if(Settings.CURRENT_STATE == Settings.STATES.GAME) {
			game.render();
		}else if(Settings.CURRENT_STATE == Settings.STATES.SETTINGS) {
			settings.render();
		}
		
		//every time stats render
		if(stats.isVisible()) {
			//stats.render(rs);
		}
		
		window.swapBuffers();
	}
	
	
	//SETTERS GETTERS
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public Graphics2D getGraphics() {
		return window.getGraphics();
	}
	
	public void nullGame() {
		game = null;
	}
	
	public void newGame() {
		game = new Game();
		game.init(this, window.getGraphics());
	}
	
	public void destroy() {
		
		window.destroy();
		System.exit(0);
		this.stop();
	}
	
	
	
	
	
}

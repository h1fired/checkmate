package com.checkmate.core;

import java.awt.Graphics2D;

import com.checkmate.core.entity.AnchorPoint;
import com.checkmate.core.entity.EntityObject;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.SpriteSheet;
import com.checkmate.core.gfx.TextureAtlas;
import com.checkmate.core.utils.Time;
import com.checkmate.state.Menu;

public class GameContainer implements Runnable {

	private String TITLE = "Checkmate";
	private int WIDTH = Settings.WIDTH;
	private int HEIGHT = Settings.HEIGHT;
	private float UPDATE_RATE = Settings.FPS;
	private int NUM_BUFFERS = 3;
	
	public static int DEFAULT_TEX_SIZE = 128;
	
	private Thread thread;
	private Display window;
	private Input input;
	private Graphics2D graphics;
	private Renderer rs;
	
	private boolean isRunning;
	
	//STATE
	private Menu menu;
	
	
	
	public GameContainer() 
	{
		isRunning = false;
		window = new Display(WIDTH, HEIGHT, TITLE, NUM_BUFFERS);
		input = new Input();
		window.setMouseListener(input);
		graphics = window.getGraphics();
		rs = new Renderer(window.getGraphics());
		
		//STATES
		menu = new Menu();
		menu.init(window.getGraphics());
		
		
		
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
		
		while(isRunning) {
			long now = Time.get();
			long elapsedTime = now - lastTime;
			lastTime = now;
			
			delta += (elapsedTime / (Time.SECOND / UPDATE_RATE));
			
			boolean render = false;
			while(delta >= 1) {
				delta--;
				
				update();
				render = true;
			}
			
			if(render) {
				render();
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
			
		}else if(Settings.CURRENT_STATE == Settings.STATES.SETTINGS) {
			
		}
		
		Input.clearMouseClick();
	}
	
	public void render() {
		window.clear();
		
		if(Settings.CURRENT_STATE == Settings.STATES.MENU) {
			menu.render();
		}else if(Settings.CURRENT_STATE == Settings.STATES.GAME) {
			
		}else if(Settings.CURRENT_STATE == Settings.STATES.SETTINGS) {
			
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
	
	
	
	
	
}

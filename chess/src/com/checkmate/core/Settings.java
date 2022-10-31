package com.checkmate.core;

public class Settings {
	
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	public static float FPS = 60.0f;
	
	public static int SINGLE_TEX_SCALE = 128;
	
	public static String TITLE = "Checkmate";
	
	public static enum STATES{
		MENU,
		SETTINGS,
		GAME
	}
	
	//SET STATE
	public static STATES CURRENT_STATE = STATES.MENU;
	
}

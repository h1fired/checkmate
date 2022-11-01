package com.checkmate.core.utils;

import java.util.HashMap;

public class Translate {
	
	public static int LANGUAGE = 1; // 1 - Ukrainian, 2 - English;
	
	public static HashMap<String, String> UKR = new HashMap<>();
	public static HashMap<String, String> ENG = new HashMap<>();	
	
	public static void setValues() {
		UKR.put("start_part", "Почати партію");
		UKR.put("exit", "Вихід");
		UKR.put("lang_choose", "Вибір мови");
		UKR.put("possible_move", "Показувати доступні ходи");
		UKR.put("volume", "Гучність");
		UKR.put("pause", "Пауза");
		UKR.put("move_count", "Кількість ходів");
		UKR.put("settings", "Налаштування");
		UKR.put("ukrainian", "Українська");
		UKR.put("english", "Англійська");
		UKR.put("menu", "Меню");
		UKR.put("go_menu", "Закінчити гру");
		UKR.put("go_continue", "Продовжити гру");
		UKR.put("checkmate", "Мат");
		UKR.put("restart", "Рестарт");
		
		ENG.put("start_part", "Start Game");
		ENG.put("exit", "Exit");
		ENG.put("lang_choose", "Choose Language");
		ENG.put("possible_move", "Show Available Moves");
		ENG.put("volume", "Volume");
		ENG.put("pause", "Pause");
		ENG.put("move_count", "Moves count");
		ENG.put("settings", "Settings");
		ENG.put("ukrainian", "Ukrainian");
		ENG.put("english", "English");
		ENG.put("menu", "Menu");
		ENG.put("go_continue", "Resume");
		ENG.put("checkmate", "Mate");
		ENG.put("restart", "Restart");
	}

	
	
	
}

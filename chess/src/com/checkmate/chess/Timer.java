package com.checkmate.chess;

import java.awt.Color;

import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.gfx.Text;
import com.checkmate.core.utils.Position;

public class Timer {

	private int tickCount;
	private int second;
	private int minute;
	private boolean isContinue;
	
	private Text time;
	
	public Timer() {
		second = 0;
		minute = 0;
		tickCount = 0;
		
		isContinue = false;
		
		time = new Text();
		time.setText(getTextTime());
		time.setPosition(0, 0);
		time.setColor(Color.black);
		time.setFontSize(27);
		time.setFont("Consolas");
	}
	
	public void update() {
		if(isContinue) {
			counter();
		}
		time.setText(getTextTime());
	}
	
	public void render(Renderer rs) {
		rs.drawString(time);
	}
	
	public void start() {
		isContinue = true;
	}
	
	public void stop() {
		isContinue = false;
	}
	
	public void reset() {
		second = 0;
		minute = 0;
		tickCount = 0;
	}
	
	
	private void counter() {
		
		if(tickCount >= Settings.FPS) {
			second++;
			tickCount = 0;
		}
		
		if(second >= 60) {
			minute++;
			second = 0;
		}
		
		if(minute >= 60)
			reset();
		
		tickCount++;
	}
	
	//GETTERS & SETTERS
	public void setPosition(int x, int y) {
		time.setPosition(x, y);
	}
	
	public Position getPosition() {
		return new Position(time.getPosition().getX(), time.getPosition().getY());
	}
	
	public Text getText() {
		return this.time;
	}
	
	public int getMinute() {
		return this.minute;
	}
	
	public int getSecond() {
		return this.second;
	}
	
	public String getTextTime() {
		if(minute < 10 && second < 10) {
			return ("0" + minute + ":0" + second);
		}
		else if(second < 10) {
			return (minute + ":0" + second);
		}
		else if(minute < 10) {
			return ("0" + minute + ":" + second);
		}else {
			return (minute + ":" + second);
		}
	}
	
	public void printTime() {
		if(tickCount >= Settings.FPS)
			if(minute < 10 && second < 10) {
				System.out.println("0" + minute + ":0" + second);
			}
			else if(second < 10) {
				System.out.println(minute + ":0" + second);
			}
			else if(minute < 10) {
				System.out.println("0" + minute + ":" + second);
			}
			else {
				System.out.println(minute + ":" + second);
			}
			
			
	}
	
	
}

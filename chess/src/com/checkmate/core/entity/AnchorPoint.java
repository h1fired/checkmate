package com.checkmate.core.entity;

import com.checkmate.core.utils.Position;

public class AnchorPoint {
	
	//AnchorPoint TYPES
	public static final int CENTERED = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int TOP = 3;
	public static final int TOP_LEFT = 4;
	public static final int TOP_RIGHT = 5;
	public static final int BOTTOM = 6;
	public static final int BOTTOM_LEFT = 7;
	public static final int BOTTOM_RIGHT = 8;
	
	
	//0
	public Position centered(int width, int height, float scale) {
		
		float sub_x = ((width * (scale + 1f)) - width) / 2;
		float sub_y = ((height * (scale + 1f)) - height) / 2;
		
		return new Position(sub_x, sub_y);
	}
	
	//1
	public Position left(int width, int height, float scale) {
		
		float sub_x = 0;
		float sub_y = ((height * (scale + 1f)) - height) / 2;
		
		return new Position(sub_x, sub_y);
	}
	
	//2
	public Position right(int width, int height, float scale) {
		
		float sub_x = ((width * (scale + 1f)) - width);
		float sub_y = ((height * (scale + 1f)) - height) / 2;
		
		return new Position(sub_x, sub_y);
	}
	
	//3
	public Position top(int width, int height, float scale) {
			
		float sub_x = ((width * (scale + 1f)) - width) / 2;
		float sub_y = 0;
			
		return new Position(sub_x, sub_y);
	}
	
	//4
	public Position topLeft(int width, int height, float scale) {
		
		float sub_x = 0;
		float sub_y = 0;
		
		return new Position(sub_x, sub_y);
	}
	
	//5
	public Position topRight(int width, int height, float scale) {
		
		float sub_x = ((width * (scale + 1f)) - width);
		float sub_y = 0;
		
		return new Position(sub_x, sub_y);
	}
	
	//6
	public Position bottom(int width, int height, float scale) {
		
		float sub_x = ((width * (scale + 1f)) - width) / 2;
		float sub_y = ((height * (scale + 1f)) - height);
		
		return new Position(sub_x, sub_y);
	}
	
	//7
	public Position bottomLeft(int width, int height, float scale) {
		
		float sub_x = 0;
		float sub_y = ((height * (scale + 1f)) - height);
		
		return new Position(sub_x, sub_y);
	}
	
	//8
	public Position bottomRight(int width, int height, float scale) {
		
		float sub_x = ((width * (scale + 1f)) - width);
		float sub_y = ((height * (scale + 1f)) - height);
		
		return new Position(sub_x, sub_y);
	}
	
	


	
	
	
}

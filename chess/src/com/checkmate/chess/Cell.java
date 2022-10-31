package com.checkmate.chess;

import java.awt.Color;

import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.components.Bounds;
import com.checkmate.core.utils.Position;

public class Cell {
	
	//Cell VARS
	private int coordY;
	private int coordX;
	
	private int cellX;
	private int cellY;
	private float cellScale;
	private Figure figure;
	
	private Bounds bounds;
	
	//booleans
	private boolean isSelect;
	
	public Cell(float scale, int coordX, int coordY) 
	{
		this.cellX = 0;
		this.cellY = 0;
		this.coordX = coordX;
		this.coordY = coordY;
		this.cellScale = scale;
		this.isSelect = false;
		
		initCell();
	}

	
	private void initCell() 
	{
		bounds = new Bounds(this.cellX, this.cellY, (int)(Settings.SINGLE_TEX_SCALE * this.cellScale), (int)(Settings.SINGLE_TEX_SCALE * this.cellScale));
		
		
	}
	
	public void drawSelection(Renderer rs) {
		rs.drawRect(2, Color.red, cellX, cellY, (int)(Settings.SINGLE_TEX_SCALE * this.cellScale), (int)(Settings.SINGLE_TEX_SCALE * this.cellScale));
	}
	
	
	// GETTERS & SETTERS
	// figure
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	public Figure getFigure() {
		return this.figure;
	}
	
	// position
	public void setPosition(int x, int y) {
		this.cellX = x;
		this.cellY = y;
		
		bounds.setBounds(x, y, (int)(Settings.SINGLE_TEX_SCALE * this.cellScale), (int)(Settings.SINGLE_TEX_SCALE * this.cellScale));
	}
	
	public Position getPosition() {
		return new Position(this.cellX, this.cellY);
	}
	
	// isContain
	public Bounds getBounds() {
		return bounds;
	}
	
	// select
	public void setSelect(boolean select) {
		isSelect = select;
	}
	
	public boolean getSelect() {
		return isSelect;
	}
	
	//coordinate
	public void setCoordinate(int x, int y) {
		this.cellX = x;
		this.cellY = y;
		
		bounds.setBounds(x, y, (int)(Settings.SINGLE_TEX_SCALE * this.cellScale), (int)(Settings.SINGLE_TEX_SCALE * this.cellScale));
	}
	
	public Position getCoordinate() {
		return new Position(this.coordX, this.coordY);
	}
	
	
	
}

package com.checkmate.chess;

import com.checkmate.core.entity.EntityObject;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.gfx.SpriteSheet;
import com.checkmate.core.gfx.TextureAtlas;

public class Figure extends EntityObject{
	
	// Texture VARS
	private SpriteSheet sheet;
	private Sprite figureTex;
	
	// Figure VARS
	private EntityObject figure;
	private String figureType;
	private boolean white;
	private boolean killed;
	
	public Figure(String type, boolean white) 
	{
		
		this.figureType = type;
		this.white = white;
		this.killed = false;
				
		// Texture Init
		initTextures();
		
		// Figure Init
		initFigure();
		
	}
	
	private void initTextures() {
		sheet = new SpriteSheet(new TextureAtlas("images/figures_texatlas_128.png").getAtlasImage());
		
		
	}
	
	
	private void initFigure() 
	{
		// white figures
		if(figureType == "king" && white) {
			figureTex = new Sprite(sheet.getSprite(0));
			this.setImage(figureTex);
		}
		if(figureType == "queen" && white) {
			figureTex = new Sprite(sheet.getSprite(1));
			this.setImage(figureTex);
		}
		if(figureType == "bishop" && white) {
			figureTex = new Sprite(sheet.getSprite(2));
			this.setImage(figureTex);
		}
		if(figureType == "knight" && white) {
			figureTex = new Sprite(sheet.getSprite(3));
			this.setImage(figureTex);
		}
		if(figureType == "rook" && white) {
			figureTex = new Sprite(sheet.getSprite(4));
			this.setImage(figureTex);
		}
		if(figureType == "pawn" && white) {
			figureTex = new Sprite(sheet.getSprite(5));
			this.setImage(figureTex);
		}
		
		//black figures
		if(figureType == "king" && !white) {
			figureTex = new Sprite(sheet.getSprite(6));
			this.setImage(figureTex);
		}
		if(figureType == "queen" && !white) {
			figureTex = new Sprite(sheet.getSprite(7));
			this.setImage(figureTex);
		}
		if(figureType == "bishop" && !white) {
			figureTex = new Sprite(sheet.getSprite(8));
			this.setImage(figureTex);
		}
		if(figureType == "knight" && !white) {
			figureTex = new Sprite(sheet.getSprite(9));
			this.setImage(figureTex);
		}
		if(figureType == "rook" && !white) {
			figureTex = new Sprite(sheet.getSprite(10));
			this.setImage(figureTex);
		}
		if(figureType == "pawn" && !white) {
			figureTex = new Sprite(sheet.getSprite(11));
			this.setImage(figureTex);
		}
		
	}
	
	
	// GETTERS & SETTERS
	public EntityObject getEntityObject() {
		return figure;
	}
	
	public boolean isWhite() {
		return white;
	}
	
	public String getType() {
		return figureType;
	}
	
	public void setEnable(boolean enable) {
		this.enableDragAnimation(enable);
		this.setBounded(enable);
		
	}
	
	public void setKilled(boolean kill) {
		if(kill) {
			killed = true;
			this.setVisible(false);
		}else {
			killed = false;
			this.setVisible(true);
		}
	}
	
	public boolean isKilled() {
		return killed;
	}
	
}

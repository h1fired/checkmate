package com.checkmate.chess;

import java.awt.Color;
import java.util.ArrayList;

import com.checkmate.core.Input;
import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.Sound;
import com.checkmate.core.components.Bounds;
import com.checkmate.core.gfx.Sprite;
import com.checkmate.core.utils.Position;
import com.checkmate.state.Game;

public class Board {
	
	// Main VARS
	private Renderer rs;
	private Move movement;
	private boolean inputActivity;
	private boolean boardActivity;
	
	// Sound
	private Sound s_wrongMove;
	private Sound s_move;
	
	// CheckMate
	private boolean checkMate;
	
	// Player VARS
	private boolean player; //TRUE - white, FALSE - black;
	private int killedKing; // 1 - white, 2 - black
	
	// board designs
	private Sprite board1_field;
	private Sprite board1_border;
	
	private Bounds bounds;
	
	private float boardScale;
	private int boardX;
	private int boardY;
	
	// Figure Draw VARS
	private Figure figures[];
	public static int prevFigureIndex;
	
	
	// Cell VARS
	private Cell cells[][];
	private Cell prevCell;
	
	private Position start;
	private Position end;
	
	
	
	//Saver VARS
	private ArrayList<Figure> killedFiguresWhite = new ArrayList<Figure>();
	private ArrayList<Figure> killedFiguresBlack = new ArrayList<Figure>();
	private int movesCount;
	
	
	
	public Board(Renderer rs, int x, int y, float scale) 
	{
		this.movement = new Move();
		this.rs = rs;
		this.boardScale = scale;
		this.boardX = x;
		this.boardY = y;
		
		this.boardActivity = true;
		this.checkMate = false;
		this.movesCount = 0;
		this.checkMate = false;
		
		this.start = new Position(0, 0);
		this.end = new Position(0, 0);
		this.killedKing = 0;
		
		initSounds();
		initBoard();
		initFigures();
		setFiguresPos();
		initCells();
		setPlayer(true);
	}
	
	public void reset() {
		//other
		killedFiguresWhite.clear();
		killedFiguresBlack.clear();
		prevCell = cells[0][0];
		
		this.boardActivity = false;
		this.checkMate = false;
		this.movesCount = 0;
		this.start = new Position(0, 0);
		this.end = new Position(0, 0);
		this.killedKing = 0;
		this.player = true;
		FigurePos.reset();
		prevFigureIndex = 0;
		
		movement.reset(this);
		
		for(int i = 0; i < 32; i++) {
			
			if(figures[i].isWhite()) {
				figures[i].setEnable(true);
			}
			
			figures[i].setVisible(true);
			figures[i].setKilled(false);
		}
		
		//cells
		int c = 0;
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				cells[i][j].setFigure(null);
				if(FigurePos.map[i][j] != 0) {
					cells[i][j].setFigure(figures[c]);
					c++;
				}
				
			}
		}
		
		
		this.boardActivity = true;
	}
	
	
	//INPUT DETECTION
	private void inputDetect() {
		
		if(true) {
			if(Input.isMousePressed() && !inputActivity && boardActivity) {
				
				for(int s = 0; s < 2; s++) {
					for(int i = 0; i < 32; i++) {
						if(figures[i].getBounds().isContain()) {
							prevFigureIndex = i;
							for(int j = 0; j < Game.getMapSize(); j++) {
								for(int c = 0; c < Game.getMapSize(); c++) {
									if(player) {
										if(cells[j][c].getFigure() != null) {
											if(cells[j][c].getBounds().isContain() && cells[j][c].getFigure().isWhite()) {
												start.setX(cells[j][c].getCoordinate().getX());
												start.setY(cells[j][c].getCoordinate().getY());
												movement.calculateMoves(this, start);
											}
										}
									}else {
										if(cells[j][c].getFigure() != null) {
											if(cells[j][c].getBounds().isContain() && !cells[j][c].getFigure().isWhite()) {
												start.setX(cells[j][c].getCoordinate().getX());
												start.setY(cells[j][c].getCoordinate().getY());
												movement.calculateMoves(this, start);
											}
										}
									}
									
								}
							}
						}
					}
				}
				
				//figuresUndetect();
				
				inputActivity = true;
			}
			
			if(!Input.isMousePressed() && inputActivity && boardActivity) {
				
				moveLogic();
				
				//dont change
				inputActivity = false;
			}
		}
		
	}

	private void initSounds() {
		
		s_move = new Sound();
		s_move.setFile("chess_move.wav");
		
		s_wrongMove = new Sound();
		s_wrongMove.setFile("wrong_move.wav");
	}
	
	private void showHints() {
		
		for(int i = 0; i < movement.getPosMoveSize(); i++) {
			
			int x_c = (int)cells[(int)movement.getPosMove(i).getX()][(int)movement.getPosMove(i).getY()].getPosition().getX();
			int y_c = (int)cells[(int)movement.getPosMove(i).getX()][(int)movement.getPosMove(i).getY()].getPosition().getY();
			
			if(cells[(int)movement.getPosMove(i).getX()][(int)movement.getPosMove(i).getY()].getFigure() != null) {
				if(cells[(int)movement.getPosMove(i).getX()][(int)movement.getPosMove(i).getY()].getFigure().getType() == "king") {
					checkMate = true;
				}
			}
			
			
			
			rs.fillOval(Color.decode("#ac3232"), x_c + 22, y_c + 22, 20, 20);
			
		}
		
		
	}
	
	//Board Init Func
	private void initBoard() 
	{
		board1_field = new Sprite("images/boardField_var1.png");
		board1_border = new Sprite("images/boardBorder_var1.png");
		
		bounds = new Bounds(this.boardX, this.boardY, (int)(board1_field.getImage().getWidth() * (boardScale + 0.5f)), (int)(board1_field.getImage().getWidth() * (boardScale + 0.5f)));
	}
	
	//Board Draw Func
	private void drawBoard() 
	{	
		rs.drawImageTile(board1_field, boardX, boardY , (int)(board1_field.getImage().getWidth() * (boardScale + 0.5f)), (int)(board1_field.getImage().getHeight() * (boardScale + 0.5f)));
		rs.drawImageTile(board1_border, boardX - 30, boardY - 30 , (int)(board1_border.getImage().getWidth() * (boardScale + 0.5f)), (int)(board1_border.getImage().getHeight() * (boardScale + 0.5f)));
	}
	
	
	//Figures Init Func
	private void initFigures() 
	{
		
		figures = new Figure[32];
		int c = 0;
		
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				
				if(FigurePos.map[i][j] / 10 == 1) {
					if(FigurePos.map[i][j] % 10 == 1) {
						figures[c] = new Figure("king", true);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 2) {
						figures[c] = new Figure("queen", true);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 3) {
						figures[c] = new Figure("bishop", true);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 4) {
						figures[c] = new Figure("knight", true);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 5) {
						figures[c] = new Figure("rook", true);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 6) {
						figures[c] = new Figure("pawn", true);
						c++;
					}
				}
				if(FigurePos.map[i][j] / 10 == 2) {
					if(FigurePos.map[i][j] % 10 == 1) {
						figures[c] = new Figure("king", false);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 2) {
						figures[c] = new Figure("queen", false);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 3) {
						figures[c] = new Figure("bishop", false);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 4) {
						figures[c] = new Figure("knight", false);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 5) {
						figures[c] = new Figure("rook", false);
						c++;
					}
					if(FigurePos.map[i][j] % 10 == 6) {
						figures[c] = new Figure("pawn", false);
						c++;
					}
				}
				
				
			}
		}
		
		for(int i = 0; i < 32; i++) {
			figures[i].setRenderScale(boardScale);
			figures[i].setLimit(bounds);
		}
		
		
	}
	
	//Figures SetPosition Func
	public void setFiguresPos() 
	{
		int c = 0;
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				if(FigurePos.map[i][j] != 0) {
					figures[c].setPosition(boardX + j * (int)(Settings.SINGLE_TEX_SCALE * boardScale) , boardY + i * (int)(Settings.SINGLE_TEX_SCALE * boardScale));
					c++;
				}
				
			}
		}
		
		
	}
	
	//Figures Active Func
	private void setActiveFigure() {
		
		if(Input.isMousePressed()) {
			for(int i = 0; i < 32; i++) {
				figures[i].setActive(false);
			}
			
			for(int i = 0; i < 32; i++) {
				if(figures[i].isContain()) {
					figures[i].setActive(true);
				}
			}
		}
		
		if(!Input.isMousePressed()) {
			for(int i = 0; i < 32; i++) {
				figures[i].setActive(true);
			}
			
		}
		
	}
	
	//Figures Set Center Position
	private void setCenterPos(int i, int j) {
		figures[prevFigureIndex].setPosition((int)cells[i][j].getPosition().getX(), (int)cells[i][j].getPosition().getY());
	}
	
	//Cells Init Func
	private void initCells() 
	{
		int c = 0;
		cells = new Cell[Game.getMapSize()][Game.getMapSize()];
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				
				cells[i][j] = new Cell(this.boardScale, i, j);
				cells[i][j].setPosition(boardX + j * (int)(Settings.SINGLE_TEX_SCALE * boardScale) , boardY + i * (int)(Settings.SINGLE_TEX_SCALE * boardScale));
				
				if(FigurePos.map[i][j] != 0) {
					cells[i][j].setFigure(figures[c]);
					c++;
				}
			}
		}
		
		prevCell = cells[0][0];
		
	}
	
	//Cells Set Position
	private void setCellPos() {
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				cells[i][j].setPosition(boardX + j * (int)(Settings.SINGLE_TEX_SCALE * boardScale) , boardY + i * (int)(Settings.SINGLE_TEX_SCALE * boardScale));
			}
		}
	}
	
	//Cell Draw Selection
	private void drawSelection() {
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				
				if(cells[i][j].getSelect()) {
					cells[i][j].drawSelection(rs);
					
				}
				
			}
		}
	}

	//Cell Set Selection
	private void setSelection() {
		
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				if(Input.isMousePressed() && cells[i][j].getBounds().isContain() && FigurePos.map[i][j] != 0) {
					cells[(int)prevCell.getCoordinate().getX()][(int)prevCell.getCoordinate().getY()].setSelect(false);
					cells[i][j].setSelect(true);
					prevCell = cells[i][j];
				}
			}
		}
		
	}
	
	//Chess Move Logic
	private void moveLogic() {
		
		
		
		if(player) {
			if(!figures[prevFigureIndex].isWhite()) {
				return;
			}
		}else {
			if(figures[prevFigureIndex].isWhite()) {
				return;
			}
		}
		
		//IF FIGURE UNZONE
		if(!bounds.isContain()) {
			int x_s = (int)cells[(int)start.getX()][(int)start.getY()].getPosition().getX();
			int y_s = (int)cells[(int)start.getX()][(int)start.getY()].getPosition().getY();
			
			figures[prevFigureIndex].setPosition(x_s, y_s);
			s_wrongMove.play();
			return;
		}
		
		
		
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				
				if(cells[i][j].getBounds().isContain()) {
					if(cells[i][j] != null) {
						
						if(player) { // WHITE PLAYER
							if(cells[i][j].getBounds().isContain()) {
								setCenterPos(i, j);
								//END COORDINATE
								if(FigurePos.map[(int)start.getX()][(int)start.getY()] / 10 == 1 || FigurePos.map[(int)start.getX()][(int)start.getY()] / 10 == 0) {
									end.setX(cells[i][j].getCoordinate().getX());
									end.setY(cells[i][j].getCoordinate().getY());
									
									//IF equals true - return
									if(end.getX() == start.getX() && end.getY() == start.getY()) {
										
										return;
									}
									
								}else {
									if(Input.isMouseClicked()) {
										return;
									}
								}
								
								
								//IF POSSIBLE MOVE
								if(!possibleMove()) {
									int x_s = (int)cells[(int)start.getX()][(int)start.getY()].getPosition().getX();
									int y_s = (int)cells[(int)start.getX()][(int)start.getY()].getPosition().getY();
									
									figures[prevFigureIndex].setPosition(x_s, y_s);
									s_wrongMove.play();
									
								}else {
									
									//IF TEAM FIGURE
									if(FigurePos.map[(int)end.getX()][(int)end.getY()] / 10 == 1) {
										setCenterPos((int)start.getX(), (int)start.getY());
										s_wrongMove.play();
									}
									
									//IF ENEMY FIGURE
									if(FigurePos.map[(int)end.getX()][(int)end.getY()] / 10 == 2) {
										
										for(int s = 0; s < 32; s++) {
											if(cells[i][j].getFigure() == figures[s]) {
												figures[s].setEnable(false);
												figures[s].setVisible(false);
												figures[s].setKilled(true);
												killedFiguresBlack.add(figures[s]);
												if(figures[s].getType() == "king") {
													killedKing = 1;
												}
												
												
											}
										}
										
										movement.moveToKilled(this, start, end);
										setCenterPos(i, j);
										
										checkmateDetect();
										
										
										s_move.play();
										setPlayer(false);
										
									}
									
									//IF CLEAR CELL
									if(FigurePos.map[(int)end.getX()][(int)end.getY()] / 10 == 0) {
										movement.moveTo(this, start, end);
										setCenterPos(i, j);
										
										checkmateDetect();
										
										s_move.play();
										setPlayer(false);
									}
									
									
									
								}
								
								
							}
							
							
						}else { //BLACK PLAYER
							if(cells[i][j].getBounds().isContain()) {
								setCenterPos(i, j);
								//END COORDINATE
								if(FigurePos.map[(int)start.getX()][(int)start.getY()] / 10 == 2 || FigurePos.map[(int)start.getX()][(int)start.getY()] / 10 == 0) {
									end.setX(cells[i][j].getCoordinate().getX());
									end.setY(cells[i][j].getCoordinate().getY());
									
									//IF equals true - return
									if(end.getX() == start.getX() && end.getY() == start.getY()) {
										
										return;
									}
									
								}else {
									if(Input.isMouseClicked()) {
										return;
									}
								}
								
								
								//IF POSSIBLE MOVE
								if(!possibleMove()) {
									int x_s = (int)cells[(int)start.getX()][(int)start.getY()].getPosition().getX();
									int y_s = (int)cells[(int)start.getX()][(int)start.getY()].getPosition().getY();
									
									figures[prevFigureIndex].setPosition(x_s, y_s);
									s_wrongMove.play();
									
								}else {
									
									//IF TEAM FIGURE
									if(FigurePos.map[(int)end.getX()][(int)end.getY()] / 10 == 2) {
										setCenterPos((int)start.getX(), (int)start.getY());
										s_wrongMove.play();
									}
									
									//IF ENEMY FIGURE
									if(FigurePos.map[(int)end.getX()][(int)end.getY()] / 10 == 1) {
										
										for(int s = 0; s < 32; s++) {
											if(cells[i][j].getFigure() == figures[s]) {
												figures[s].setEnable(false);
												figures[s].setVisible(false);
												figures[s].setKilled(true);
												killedFiguresWhite.add(figures[s]);
												if(figures[s].getType() == "king") {
													killedKing = 2;
												}
												
												
											}
										}
										
										movement.moveToKilled(this, start, end);
										setCenterPos(i, j);
										
										checkmateDetect();
										
										
										s_move.play();
										setPlayer(true);
										
									}
									
									//IF CLEAR CELL
									if(FigurePos.map[(int)end.getX()][(int)end.getY()] / 10 == 0) {
										movement.moveTo(this, start, end);
										setCenterPos(i, j);
										
										checkmateDetect();
										
										s_move.play();
										setPlayer(true);
									}
									
									
									
								}
								
								
							}
						}
						
					}
					
				}
				
				
			}
		}
		
		FigurePos.print();
		
	}
	
	//possible move
	private boolean possibleMove() {
		movement.calculateMoves(this, start);
		
		boolean isMove = false;
		for(int s = 0; s < movement.getPosMoveSize(); s++) {
			
			System.out.println("Poss Moves: X = (" + movement.getPosMove(s).getX() + ") || Y = (" + movement.getPosMove(s).getY() + ")");
			
			if(end.getX() == movement.getPosMove(s).getX() && end.getY() == movement.getPosMove(s).getY()) {
				isMove = true;
			}
		}
		
		return isMove;
	}

	public void setActive(boolean active) {
		for(int i = 0; i < 32; i++) {
			figures[i].enableDragAnimation(active);
			figures[i].setEnable(active);
			inputActivity = active;
			boardActivity = active;
		}
	}
	
	//RENDER & UPDATE
	public void render() {
		
		//printPoses();
		
		drawBoard();
		if(inputActivity) {
			drawSelection();
			showHints();
		}
		
		
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				if(cells[i][j].getFigure() != null && cells[i][j].getBounds().isContain()) {
					//System.out.println("FIGURE: type = (" + cells[i][j].getFigure().getType() + "; " + cells[i][j].getFigure().isWhite());
				}
			}
		}
		
		for(int i = 0; i < 32; i++) {
			if(figures[i].isVisible()) {
				rs.drawEntityObject(figures[i]);
			}
		}
		if(figures[Board.prevFigureIndex].isVisible()) {
			rs.drawEntityObject(figures[prevFigureIndex]);
		}
		
		
	}
	
	public void update() {
		
		//printPoses();
		
		if(boardActivity) {
			for(int i = 0; i < 32; i++) {
				figures[i].DragAnimation(figures[i]);
			}
			setActiveFigure();
			
			setSelection();
			inputDetect();
		}
		
		
	}
	
	
	//GETTERS & SETTERS
	public void setPosition(int x, int y) {
		this.boardX = x;
		this.boardY = y;
		
		setFiguresPos();
		setCellPos();
	}
	
	public Position getPosition() {
		return new Position(this.boardX, this.boardY);
	}
	
	
	//player
	public void setPlayer(boolean white) {
		this.player = white;
		
		if(player) {
			for(int i = 0; i < 32; i++) {
				if(!figures[i].isWhite()) {
					figures[i].setEnable(false);
				}
				if(figures[i].isWhite()) {
					figures[i].setEnable(true);
				}
			}
		}else {
			for(int i = 0; i < 32; i++) {
				if(!figures[i].isWhite()) {
					figures[i].setEnable(true);
				}
				if(figures[i].isWhite()) {
					figures[i].setEnable(false);
				}
			}
		}
	}
	
	//entitys
	public Figure getFigures(int index) {
		return figures[index];
	}
	
	//cell
	public Cell getCell(int indexX, int indexY) {
		if(indexX >= 0 && indexX <= 7 && indexY >= 0 && indexY <= 7) {
			return cells[indexX][indexY];
		}
		return null;
	}
	
	//player
	public boolean getPlayer() {
		return player;
	}
	
	//move pos null
	private void posNull() {
		start.setX(0f);
		start.setY(0f);
		end.setX(0f);
		end.setY(0f);
		
	}
	
	public Bounds getBounds() {
		return bounds;
	}
	
	//side win
	public int getWinSide() {
		return killedKing;
	}
	
	//moves count
	public int getMovesCount() {
		return movesCount;
	}
	
	//checkmate detect
	private void checkmateDetect() {
		checkMate = movement.checkMateDetect(this, end);
		
		
	}
	
	public boolean isCheckmate() {
		
		return checkMate;
	}
	
	//OTHER
	public void printPoses() {
		System.out.println("MOVE: start = (" + start.getX() + "; " + start.getY() + "), end = (" + end.getX() + "; " + end.getY() + ")");
	}
	
}

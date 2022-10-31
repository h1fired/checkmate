package com.checkmate.chess;

import java.util.ArrayList;

import com.checkmate.core.utils.Position;
import com.checkmate.state.Game;

public class Move {
	
	private ArrayList<Position> possibleMove = new ArrayList<Position>();
	private ArrayList<Position> possibleMoveAll = new ArrayList<Position>();
	
	private int moveSide;
	private boolean detectSide;
	private int startPawnPos;
	
	public void moveTo(Board board, Position start, Position end) 
	{
		
		int tmp = FigurePos.map[(int)start.getX()][(int)start.getY()];
		FigurePos.map[(int)start.getX()][(int)start.getY()] = FigurePos.map[(int)end.getX()][(int)end.getY()];
		FigurePos.map[(int)end.getX()][(int)end.getY()] = tmp;
		
		board.getCell((int)end.getX(), (int)end.getY()).setFigure(board.getCell((int)start.getX(), (int)start.getY()).getFigure());
		board.getCell((int)start.getX(), (int)start.getY()).setFigure(null);
		
		//FigurePos.printMap();
	}
	
	public void moveToKilled(Board board, Position start, Position end) 
	{
		
		FigurePos.map[(int)end.getX()][(int)end.getY()] = FigurePos.map[(int)start.getX()][(int)start.getY()];
		FigurePos.map[(int)start.getX()][(int)start.getY()] = 0;
		
		board.getCell((int)end.getX(), (int)end.getY()).setFigure(board.getCell((int)start.getX(), (int)start.getY()).getFigure());
		board.getCell((int)start.getX(), (int)start.getY()).setFigure(null);
		
		//FigurePos.printMap();
	}
	
	//detect all moves
	public void movesAllDetect(Board board) {
		possibleMoveAll.clear();
		
		for(int i = 0; i < Game.getMapSize(); i++) {
			for(int j = 0; j < Game.getMapSize(); j++) {
				if(board.getCell(i, j) != null) {
					if(board.getCell(i, j).getFigure() != null) {
						
						if(board.getCell(i, j).getFigure().isWhite()) {
							detectSide = false;
						}else {
							detectSide = true;
						}
						
						if(board.getCell(i, j).getFigure().getType() == "pawn"){
							pawnDetect(board, new Position(i, j));
						}
						if(board.getCell(i, j).getFigure().getType() == "rook"){
							rookDetect(board, new Position(i, j));
						}
						if(board.getCell(i, j).getFigure().getType() == "knight"){
							knightDetect(board, new Position(i, j));
						}
						if(board.getCell(i, j).getFigure().getType() == "bishop"){
							bishopDetect(board, new Position(i, j));
						}
						if(board.getCell(i, j).getFigure().getType() == "queen"){
							queenDetect(board, new Position(i, j));
						}
						
						
					}
				}
			}
		}
		
		for(int i = 0; i < possibleMoveAll.size(); i++) {
			System.out.println("DETECT = x: " + possibleMoveAll.get(i).getX() + " || y: " + possibleMoveAll.get(i).getY());
		}
	}
	
	//Checkmate
	public boolean checkMateDetect(Board board, Position start) {
		calculateMoves(board, start);
		
		for(int s = 0; s < possibleMove.size(); s++) {
			for(int i = 0; i < Game.getMapSize(); i++) {
				for(int j = 0; j < Game.getMapSize(); j++) {
					if(board.getCell((int)possibleMove.get(s).getX(), (int)possibleMove.get(s).getY()).getFigure() != null){
						if(board.getCell((int)possibleMove.get(s).getX(), (int)possibleMove.get(s).getY()).getFigure().getType() == "king"){
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	//Moves Calculation
	
	public void calculateMoves(Board board, Position start) {
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().isWhite()) {
			moveSide = 1;
			startPawnPos = 6;
			detectSide = false;
		}else {
			moveSide = -1;
			startPawnPos = 1;
			detectSide = true;
		}
		
		possibleMove.clear();
		
		if(start.getX() >= 0 && start.getX() <= 7 || start.getY() >= 0 && start.getY() <= 7) {
			
		}else {
			return;
		}
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().getType() == "pawn") {
			pawnDetect(board, start);
			return;
		}
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().getType() == "rook") {
			rookDetect(board, start);
			return;
		}
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().getType() == "knight") {
			knightDetect(board, start);
			return;
		}
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().getType() == "bishop") {
			bishopDetect(board, start);
			return;
		}
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().getType() == "queen") {
			queenDetect(board, start);
			return;
		}
		
		if(board.getCell((int)start.getX(), (int)start.getY()).getFigure().getType() == "king") {
			kingDetect(board, start);
			return;
		}
		
		
		
		
	}
	
	private void pawnDetect(Board board, Position start) {
		
		//2 cell start clear move
		if(board.getCell((int)start.getX() - (2 * moveSide), (int)start.getY()) != null) {
			if((int)start.getX() == startPawnPos && board.getCell((int)start.getX() - (2 * moveSide), (int)start.getY()).getFigure() == null && board.getCell((int)start.getX() - moveSide, (int)start.getY()).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - (2 * moveSide), (int)start.getY()));
				possibleMoveAll.add(new Position((int)start.getX() - (2 * moveSide), (int)start.getY()));
			}
		}
		
		//1 cell clear move
		if(board.getCell((int)start.getX() - moveSide, (int)start.getY()) != null) {
			if(board.getCell((int)start.getX() - moveSide, (int)start.getY()).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - moveSide, (int)start.getY()));
				possibleMoveAll.add(new Position((int)start.getX() - moveSide, (int)start.getY()));
			}
		}
		
		//kill left
		if(board.getCell((int)start.getX() - moveSide, (int)start.getY() - moveSide) != null) {
			if(board.getCell((int)start.getX() - moveSide, (int)start.getY() - moveSide).getFigure() != null) {
				if(board.getCell((int)start.getX() - moveSide, (int)start.getY() - moveSide).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() - moveSide, (int)start.getY() - moveSide));
					possibleMoveAll.add(new Position((int)start.getX() - moveSide, (int)start.getY() - moveSide));
				}
			}
		}
		
		//kill right
		if(board.getCell((int)start.getX() - moveSide, (int)start.getY() + moveSide) != null) {
			if(board.getCell((int)start.getX() - moveSide, (int)start.getY() + moveSide).getFigure() != null  && board.getCell((int)start.getX() - moveSide, (int)start.getY() - moveSide) != null) {
				if(board.getCell((int)start.getX() - moveSide, (int)start.getY() + moveSide).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() - moveSide, (int)start.getY() + moveSide));
					possibleMoveAll.add(new Position((int)start.getX() - moveSide, (int)start.getY() + moveSide));
				}
			}
		}
		
	}
	
	private void rookDetect(Board board, Position start) {	
		//move vertical top
		for(int i = (int)start.getX() - 1; i >= 0; i--) {
			if(board.getCell(i, (int)start.getY() ) != null && board.getCell(i, (int)start.getY()).getFigure() == null) {
				possibleMove.add(new Position(i, (int)start.getY()));
				possibleMoveAll.add(new Position(i, (int)start.getY()));
			}else {
				break;
			}
		}
		for(int i = (int)start.getX() - 1; i >= 0; i--) {
			if(board.getCell(i, (int)start.getY()) != null && board.getCell(i, (int)start.getY()).getFigure() != null) {
				if(board.getCell(i, (int)start.getY()).getFigure().isWhite() == !detectSide) {
					break;
				}
				
				if(board.getCell(i, (int)start.getY()).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position(i, (int)start.getY()));
					possibleMoveAll.add(new Position(i, (int)start.getY()));
					break;
				}
			}
		}
		
		//move vertical bottom
		for(int i = (int)start.getX() + 1; i <= 7; i++) {
			if(board.getCell(i, (int)start.getY() ) != null && board.getCell(i, (int)start.getY()).getFigure() == null) {
				possibleMove.add(new Position(i, (int)start.getY()));
				possibleMoveAll.add(new Position(i, (int)start.getY()));
			}else {
				break;
			}
		}
		for(int i = (int)start.getX() + 1; i <= 7; i++) {
			if(board.getCell(i, (int)start.getY()) != null && board.getCell(i, (int)start.getY()).getFigure() != null) {
				if(board.getCell(i, (int)start.getY()).getFigure().isWhite() == !detectSide) {
					break;
				}
				
				if(board.getCell(i, (int)start.getY()).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position(i, (int)start.getY()));
					possibleMoveAll.add(new Position(i, (int)start.getY()));
					break;
				}
			}
		}
		
		//move horizontal left
		for(int i = (int)start.getY() - 1; i >= 0; i--) {
			if(board.getCell((int)start.getX(), i) != null && board.getCell((int)start.getX(), i).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX(), i));
				possibleMoveAll.add(new Position((int)start.getX(), i));
			}else {
				break;
			}
		}
		for(int i = (int)start.getY() - 1; i >= 0; i--) {
			if(board.getCell((int)start.getX(), i) != null && board.getCell((int)start.getX(), i).getFigure() != null) {
				if(board.getCell((int)start.getX(), i).getFigure().isWhite() == !detectSide) {
					break;
				}
				
				if(board.getCell((int)start.getX(), i).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX(), i));
					possibleMoveAll.add(new Position((int)start.getX(), i));
					break;
				}
			}
		}
		
		//move horizontal right
		for(int i = (int)start.getY() + 1; i <= 7; i++) {
			if(board.getCell((int)start.getX(), i) != null && board.getCell((int)start.getX(), i).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX(), i));
				possibleMoveAll.add(new Position((int)start.getX(), i));
			}else {
				break;
			}
		}
		for(int i = (int)start.getY() + 1; i <= 7; i++) {
			if(board.getCell((int)start.getX(), i) != null && board.getCell((int)start.getX(), i).getFigure() != null) {
				if(board.getCell((int)start.getX(), i).getFigure().isWhite() == !detectSide) {
					break;
				}
				
				if(board.getCell((int)start.getX(), i).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX(), i));
					possibleMoveAll.add(new Position((int)start.getX(), i));
					break;
				}
			}
		}
		
		
	}
	
	private void knightDetect(Board board, Position start) {
		
		//topleft
		if(board.getCell((int)start.getX() - 2, (int)start.getY() - 1) != null) {
			if(board.getCell((int)start.getX() - 2, (int)start.getY() - 1).getFigure() != null) {
				if(board.getCell((int)start.getX() - 2, (int)start.getY() - 1).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() - 2, (int)start.getY() - 1));
					possibleMoveAll.add(new Position((int)start.getX() - 2, (int)start.getY() - 1));
				}
					
			}
			if(board.getCell((int)start.getX() - 2, (int)start.getY() - 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 2, (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX() - 2, (int)start.getY() - 1));
			}
		}
		
		
		
		//topright
		if(board.getCell((int)start.getX() - 2, (int)start.getY() + 1) != null) {
			if(board.getCell((int)start.getX() - 2, (int)start.getY() + 1).getFigure() != null) {
				if(board.getCell((int)start.getX() - 2, (int)start.getY() + 1).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() - 2, (int)start.getY() + 1));
					possibleMoveAll.add(new Position((int)start.getX() - 2, (int)start.getY() + 1));
				}	
			}
			if(board.getCell((int)start.getX() - 2, (int)start.getY() + 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 2, (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX() - 2, (int)start.getY() + 1));
			}
		}
		
		
		
		//bottomleft
		if(board.getCell((int)start.getX() + 2, (int)start.getY() - 1) != null ) {
			if(board.getCell((int)start.getX() + 2, (int)start.getY() - 1).getFigure() != null) {
				if(board.getCell((int)start.getX() + 2, (int)start.getY() - 1).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() + 2, (int)start.getY() - 1));
					possibleMoveAll.add(new Position((int)start.getX() + 2, (int)start.getY() - 1));
				}
					
			}
			if(board.getCell((int)start.getX() + 2, (int)start.getY() - 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 2, (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX() + 2, (int)start.getY() - 1));
			}
		}
		
		//bottom right
		if(board.getCell((int)start.getX() + 2, (int)start.getY() + 1) != null ) {
			if(board.getCell((int)start.getX() + 2, (int)start.getY() + 1).getFigure() != null) {
				if(board.getCell((int)start.getX() + 2, (int)start.getY() + 1).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() + 2, (int)start.getY() + 1));
					possibleMoveAll.add(new Position((int)start.getX() + 2, (int)start.getY() + 1));
				}
					
			}
			if(board.getCell((int)start.getX() + 2, (int)start.getY() + 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 2, (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX() + 2, (int)start.getY() + 1));
			}
		}
		
		//rightbottom
		if(board.getCell((int)start.getX() + 1, (int)start.getY() + 2) != null ) {
			if(board.getCell((int)start.getX() + 1, (int)start.getY() + 2).getFigure() != null) {
				if(board.getCell((int)start.getX() + 1, (int)start.getY() + 2).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() + 2));
					possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() + 2));
				}
					
			}
			if(board.getCell((int)start.getX() + 1, (int)start.getY() + 2).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() + 2));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() + 2));
			}
		}
		
		//leftbottom
		if(board.getCell((int)start.getX() + 1, (int)start.getY() - 2) != null ) {
			if(board.getCell((int)start.getX() + 1, (int)start.getY() - 2).getFigure() != null) {
				if(board.getCell((int)start.getX() + 1, (int)start.getY() - 2).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() - 2));
					possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() - 2));
				}
					
			}
			if(board.getCell((int)start.getX() + 1, (int)start.getY() - 2).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() - 2));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() - 2));
			}
		}
		
		//righttop
		if(board.getCell((int)start.getX() - 1, (int)start.getY() - 2) != null ) {
			if(board.getCell((int)start.getX() - 1, (int)start.getY() - 2).getFigure() != null) {
				if(board.getCell((int)start.getX() - 1, (int)start.getY() - 2).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() - 2));
					possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() - 2));
				}
					
			}
			if(board.getCell((int)start.getX() - 1, (int)start.getY() - 2).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() - 2));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() - 2));
			}
		}
		
		//lefttop
		if(board.getCell((int)start.getX() - 1, (int)start.getY() + 2) != null ) {
			if(board.getCell((int)start.getX() - 1, (int)start.getY() + 2).getFigure() != null) {
				if(board.getCell((int)start.getX() - 1, (int)start.getY() + 2).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() + 2));
					possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() + 2));
				}
					
			}
			if(board.getCell((int)start.getX() - 1, (int)start.getY() + 2).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() + 2));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() + 2));
			}
		}
	}
	
	private void bishopDetect(Board board, Position start) {
		
		int j = 0;
		//top left
		j = (int)start.getY() - 1;
		for(int i = (int)start.getX() - 1; i >= 0; i--) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() == null) {
				possibleMove.add(new Position(i, j));
				possibleMoveAll.add(new Position(i, j));
			}else {
				break;
			}
			if(j >= 0) {
				j--;
			}else {
				break;
			}
		}
		
		j = (int)start.getY() - 1;
		for(int i = (int)start.getX() - 1; i >= 0; i--) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() != null) {
				if(board.getCell(i, j).getFigure().isWhite() == !detectSide) {
					break;
				}
				if(board.getCell(i, j).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position(i, j));
					possibleMoveAll.add(new Position(i, j));
					break;
				}
			}
			if(j >= 0) {
				j--;
			}else {
				break;
			}
		}
		
		//top right
		j = (int)start.getY() + 1;
		for(int i = (int)start.getX() - 1; i >= 0; i--) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() == null) {
				possibleMove.add(new Position(i, j));
				possibleMoveAll.add(new Position(i, j));
			}else {
				break;
			}
			if(j <= 7) {
				j++;
			}else {
				break;
			}
		}
		
		j = (int)start.getY() + 1;
		for(int i = (int)start.getX() - 1; i >= 0; i--) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() != null) {
				if(board.getCell(i, j).getFigure().isWhite() == !detectSide) {
					break;
				}
				if(board.getCell(i, j).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position(i, j));
					possibleMoveAll.add(new Position(i, j));
					break;
				}
			}
			if(j <= 7) {
				j++;
			}else {
				break;
			}
		}
		
		//bottom left
		j = (int)start.getY() - 1;
		for(int i = (int)start.getX() + 1; i <= 7; i++) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() == null) {
				possibleMove.add(new Position(i, j));
				possibleMoveAll.add(new Position(i, j));
			}else {
				break;
			}
			if(j >= 0) {
				j--;
			}else {
				break;
			}
		}
		
		j = (int)start.getY() - 1;
		for(int i = (int)start.getX() + 1; i <= 7; i++) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() != null) {
				if(board.getCell(i, j).getFigure().isWhite() == !detectSide) {
					break;
				}
				if(board.getCell(i, j).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position(i, j));
					possibleMoveAll.add(new Position(i, j));
					break;
				}
			}
			if(j >= 0) {
				j--;
			}else {
				break;
			}
		}
		
		//bottom right
		j = (int)start.getY() + 1;
		for(int i = (int)start.getX() + 1; i <= 7; i++) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() == null) {
				possibleMove.add(new Position(i, j));
				possibleMoveAll.add(new Position(i, j));
			}else {
				break;
			}
			if(j <= 7) {
				j++;
			}else {
				break;
			}
		}
		
		j = (int)start.getY() + 1;
		for(int i = (int)start.getX() + 1; i <= 7; i++) {
			if(board.getCell(i, j) != null && board.getCell(i, j).getFigure() != null) {
				if(board.getCell(i, j).getFigure().isWhite() == !detectSide) {
					break;
				}
				if(board.getCell(i, j).getFigure().isWhite() == detectSide) {
					possibleMove.add(new Position(i, j));
					possibleMoveAll.add(new Position(i, j));
					break;
				}
			}
			if(j <= 7) {
				j++;
			}else {
				break;
			}
		}
		
		
	}

	private void queenDetect(Board board, Position start) {
		rookDetect(board, start);
		bishopDetect(board, start);
	}

	private void kingDetect(Board board, Position start) {
		
		//top
		if(board.getCell((int)start.getX() - 1, (int)start.getY()) != null) {
			if(board.getCell((int)start.getX() - 1, (int)start.getY()).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY()));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY()));
			}
			else if(board.getCell((int)start.getX() - 1, (int)start.getY()).getFigure() != null && board.getCell((int)start.getX() - 1, (int)start.getY()).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY()));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY()));
			}
		}
		
		//topleft
		if(board.getCell((int)start.getX() - 1, (int)start.getY() - 1) != null) {
			if(board.getCell((int)start.getX() - 1, (int)start.getY() - 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() - 1));
			}
			else if(board.getCell((int)start.getX() - 1, (int)start.getY() - 1).getFigure() != null && board.getCell((int)start.getX() - 1, (int)start.getY() - 1).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() - 1));
			}
		}
		
		//topright
		if(board.getCell((int)start.getX() - 1, (int)start.getY() + 1) != null) {
			if(board.getCell((int)start.getX() - 1, (int)start.getY() + 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() + 1));
			}
			else if(board.getCell((int)start.getX() - 1, (int)start.getY() + 1).getFigure() != null && board.getCell((int)start.getX() - 1, (int)start.getY() + 1).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX() - 1, (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX() - 1, (int)start.getY() + 1));
			}
		}
		
		//left
		if(board.getCell((int)start.getX(), (int)start.getY() - 1) != null) {
			if(board.getCell((int)start.getX(), (int)start.getY() - 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX(), (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX(), (int)start.getY() - 1));
			}
			else if(board.getCell((int)start.getX(), (int)start.getY() - 1).getFigure() != null && board.getCell((int)start.getX(), (int)start.getY() - 1).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX(), (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX(), (int)start.getY() - 1));
			}
		}
		
		//right
		if(board.getCell((int)start.getX(), (int)start.getY() + 1) != null) {
			if(board.getCell((int)start.getX(), (int)start.getY() + 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX(), (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX(), (int)start.getY() + 1));
			}
			else if(board.getCell((int)start.getX(), (int)start.getY() + 1).getFigure() != null && board.getCell((int)start.getX(), (int)start.getY() + 1).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX(), (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX(), (int)start.getY() + 1));
			}
		}
		
		//bottom
		if(board.getCell((int)start.getX() + 1, (int)start.getY()) != null) {
			if(board.getCell((int)start.getX() + 1, (int)start.getY()).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY()));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY()));
			}
			else if(board.getCell((int)start.getX() + 1, (int)start.getY()).getFigure() != null && board.getCell((int)start.getX() + 1, (int)start.getY()).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY()));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY()));
			}
		}
		
		//bottomleft
		if(board.getCell((int)start.getX() + 1, (int)start.getY() - 1) != null) {
			if(board.getCell((int)start.getX() + 1, (int)start.getY() - 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() - 1));
			}
			else if(board.getCell((int)start.getX() + 1, (int)start.getY() - 1).getFigure() != null && board.getCell((int)start.getX() + 1, (int)start.getY() - 1).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() - 1));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() - 1));
			}
		}
		
		//bottomright
		if(board.getCell((int)start.getX() + 1, (int)start.getY() + 1) != null) {
			if(board.getCell((int)start.getX() + 1, (int)start.getY() + 1).getFigure() == null) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() + 1));
			}
			else if(board.getCell((int)start.getX() + 1, (int)start.getY() + 1).getFigure() != null && board.getCell((int)start.getX() + 1, (int)start.getY() + 1).getFigure().isWhite() == detectSide) {
				possibleMove.add(new Position((int)start.getX() + 1, (int)start.getY() + 1));
				possibleMoveAll.add(new Position((int)start.getX() + 1, (int)start.getY() + 1));
			}
		}
		
	}
	
	//get Possible Positions
	public int getPosMoveSize() {
		return possibleMove.size();
	}
	
	public Position getPosMove (int index) {
		return possibleMove.get(index);
	}
	
}

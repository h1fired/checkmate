package com.checkmate.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.checkmate.core.utils.Position;

public class Input implements MouseListener, MouseMotionListener{
	
	private static Position mousePosition;
	private static boolean isMouseClicked;
	private static boolean isMousePressed;
	private static boolean isMouseReleased;
	
	public Input() {
		mousePosition = new Position(0, 0);
	}
	
	public static Position getMousePosition() {
		return mousePosition;
	}
	
	public static boolean isMouseClicked() {
		return isMouseClicked;
	}
	
	public static boolean isMousePressed() {
		return isMousePressed;
	}
	
	public static boolean isMouseReleased() {
		return isMouseReleased;
	}
	
	public static void clearMouseClick() {
		isMouseClicked = false;
		isMouseReleased = false;
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosition = new Position(e.getX(), e.getY());
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = new Position(e.getX(), e.getY());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		isMouseClicked = true;
		mousePosition = new Position(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isMousePressed = true;
		mousePosition = new Position(e.getX(), e.getY());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isMouseClicked = false;
		isMousePressed = false;
		isMouseReleased = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

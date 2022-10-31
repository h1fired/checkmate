package com.checkmate.core.components;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import com.checkmate.core.Animation;
import com.checkmate.core.Renderer;

public class Slider extends Animation {
	
	private Bounds boundsRect;
	private Bounds boundsEllipse;
	
	private Rectangle slideRangeRect;
	private Ellipse2D slideEllipse;
	
	private float VALUE;
	
	public Slider(int x, int y, int width, int thickness, int radius) {
		
		slideRangeRect = new Rectangle(x, y - thickness / 2, width, thickness);
		slideEllipse = new Ellipse2D.Double(x + (width / 2) - radius / 2, y - radius / 2, radius, radius);
		
		boundsEllipse = new Bounds((int)slideEllipse.getX(), (int)slideEllipse.getY(), (int)slideEllipse.getWidth(), (int)slideEllipse.getHeight());
		boundsRect = new Bounds((int)slideRangeRect.getX(), (int)slideRangeRect.getY(), (int)slideRangeRect.getWidth(), (int)slideRangeRect.getHeight());
	}
	
	private void valueDetect() {
		float min = (float)slideRangeRect.getX();
		float max = min + (float)slideRangeRect.getWidth();
		float value_range = max - min;
		float ellipsePos = (float)slideEllipse.getX() + (float)slideEllipse.getWidth() / 2 - min;
		VALUE = ((ellipsePos * 100f) / value_range) / 2 - 50;
		
	}
	
	public void render(Renderer rs) {
		rs.fillRectangle2D(slideRangeRect);
		rs.fillEllipse(slideEllipse);
		
	}
	
	public void update() {
		this.DragAnimationSlider(this);
		valueDetect();
	}
	
	//GETTERS & SETTERS
	public float getValue() {
		return VALUE;
	}
	
	public Bounds getRectBounds() {
		return boundsRect;
	}
	
	public Bounds getEllipseBounds() {
		return boundsEllipse;
	}
	
	public Ellipse2D getEllipse() {
		return slideEllipse;
	}
	
	public Rectangle getRect() {
		return slideRangeRect;
	}
	
}

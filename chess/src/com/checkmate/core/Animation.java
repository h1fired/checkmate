package com.checkmate.core;

import com.checkmate.core.components.Slider;
import com.checkmate.core.entity.EntityObject;

public class Animation {

	private boolean DRAG_ISANIMATED;

	private boolean UPDATE_STOP;
	private boolean CONTAIN_ANIM;

	private int shifted_x;
	private int shifted_y;
	private int contain_x;
	private int contain_y;

	
	public void DragAnimation(EntityObject sprite) {
		if (sprite.isContain()) {
			CONTAIN_ANIM = true;
		}
		if (Input.isMousePressed() && CONTAIN_ANIM && sprite.isAnimatedDrag()) {

			DRAG_ISANIMATED = true;

			if (!UPDATE_STOP) {

				shifted_x = (int) (Input.getMousePosition().getX() - sprite.getBounds().getX() - sprite.getScaledPos().getX());
				shifted_y = (int) (Input.getMousePosition().getY() - sprite.getBounds().getY() - sprite.getScaledPos().getY());

			}
			UPDATE_STOP = true;
			
			this.contain_x = (int) Input.getMousePosition().getX() - shifted_x;
			this.contain_y = (int) Input.getMousePosition().getY() - shifted_y;
			sprite.setPosition(contain_x, contain_y);
			
		}
		if (!Input.isMousePressed()) {
			UPDATE_STOP = false;
			CONTAIN_ANIM = false; 
			DRAG_ISANIMATED = false;

		}
	}
	
	public void DragAnimationSlider(Slider slider) {
		
		if (slider.getEllipseBounds().isContain()) {
			CONTAIN_ANIM = true;
		}
		
		if(Input.isMousePressed() && CONTAIN_ANIM) {
			
			if((int)Input.getMousePosition().getX() >= slider.getRect().getX() && (int)Input.getMousePosition().getX() <= slider.getRect().getMaxX()) {
				int x_s = (int)Input.getMousePosition().getX() - (int)(slider.getEllipse().getWidth() / 2) ;
				
				slider.getEllipse().setFrame(x_s, slider.getEllipse().getY(), (slider.getEllipse().getWidth()), (slider.getEllipse().getHeight()));
				slider.getEllipseBounds().setBounds(x_s, (int)slider.getEllipse().getY(), (int)(slider.getEllipse().getWidth()), (int)(slider.getEllipse().getHeight()));
			}
			
		}
		if (!Input.isMousePressed()) {
			UPDATE_STOP = false;
			CONTAIN_ANIM = false; 
			DRAG_ISANIMATED = false;

		}
		
	}

	// GETTERS SETTERS

	// Статус анімації
	public boolean getDragStatus() {
		return DRAG_ISANIMATED;
	}

}

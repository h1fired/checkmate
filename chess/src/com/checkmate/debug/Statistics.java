package com.checkmate.debug;

import java.awt.Color;

import com.checkmate.core.GameContainer;
import com.checkmate.core.Renderer;
import com.checkmate.core.Settings;
import com.checkmate.core.gfx.Text;

public class Statistics {
	
	private boolean visible;
	
	private Text fps;
	private Text upd;
	private Text updl;
	
	public Statistics() {
		visible = true;
		
		fps = new Text("FPS: " + GameContainer.curFPS);
		fps.setColor(Color.white);
		fps.setFontSize(14);
		fps.setFont("Arial");
		
		upd = new Text("UPD: " + GameContainer.curUPD);
		upd.setColor(Color.white);
		upd.setFontSize(14);
		upd.setFont("Arial");
		
		updl = new Text("UPDL: " + GameContainer.curUPDL);
		updl.setColor(Color.white);
		updl.setFontSize(14);
		updl.setFont("Arial");
		
	}
	
	public void render(Renderer rs) 
	{	
		fps.setText("FPS: " + GameContainer.curFPS);
		fps.setPosition(Settings.WIDTH - fps.getWidth(), 0);
		rs.drawString(fps);
		
		upd.setText("UPD: " + GameContainer.curUPD);
		upd.setPosition(Settings.WIDTH - upd.getWidth(), 0 + fps.getHeight());
		rs.drawString(upd);
		
		updl.setText("UPDL: " + GameContainer.curUPDL);
		updl.setPosition(Settings.WIDTH - updl.getWidth(), 0 + fps.getHeight() + upd.getHeight());
		rs.drawString(updl);
		
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
}

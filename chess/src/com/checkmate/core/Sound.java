package com.checkmate.core;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	public static float VOLUME = -25.0f;
	private Clip clip;
	
	public void setFile(String fileName) {
		
		try {
			File file = new File("res/sounds/" + fileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			
			try {
				clip = AudioSystem.getClip();
				clip.open(sound);
				
				
				
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		volume(VOLUME);
		
	}
	
	public void volume(float volume) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
		System.out.println(volume);
	}
	
	public void play() {
		clip.setMicrosecondPosition(0);
		clip.start();
	}
	
	

	
	
}

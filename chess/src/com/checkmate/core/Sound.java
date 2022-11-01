package com.checkmate.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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
		
		String fullpath = "/com/checkmate/Resources/sounds/" + fileName;
		
		InputStream audioSrc = Sound.class.getResourceAsStream(fullpath);
		
		InputStream bufferedIn = new BufferedInputStream(audioSrc);
		
		try {
			
			AudioInputStream sound = AudioSystem.getAudioInputStream(bufferedIn);
			
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

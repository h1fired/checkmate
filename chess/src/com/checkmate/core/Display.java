package com.checkmate.core;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;

public class Display {
	
	private boolean isCreated;
	
	private Canvas canvas;
	private JFrame frame;
	
	private BufferedImage buffer;
	private int[] bufferData;
	private Graphics bufferGraphics;
	private BufferStrategy bufferStrategy;
	private int clearColor;
	
	
	
	public Display(int width, int height, String title, int numBuffers) 
	{
		isCreated = true;

		canvas = new Canvas();
		Dimension size = new Dimension(width, height);
		canvas.setPreferredSize(size);
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Створення графіки
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
		bufferGraphics = buffer.getGraphics();
		((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		clearColor = 0xff000000;
		
		//Мультибуферизація
		canvas.createBufferStrategy(numBuffers);
		bufferStrategy = canvas.getBufferStrategy();
		
	}
	
	
	//Очищення буфера
	public void clear() 
	{
		Arrays.fill(bufferData, clearColor);
	}
	
	// Заміна стандартного буфера на створений buffer
	public void swapBuffers() 
	{
		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(buffer, 0, 0, null);
		bufferStrategy.show();
	}
	
	//Дозволяє отримати компонент графіки
	public Graphics2D getGraphics() {
		return (Graphics2D) bufferGraphics;
	}
	
	//Закриває вікно та зв'язані процеси
	public void destroy() {
		if(!isCreated)
			return;
		
		isCreated = false;
		frame.dispose();
	}
	
	//Встановлення назви вікна гри
	public void setTitle(String title) 
	{
		frame.setTitle(title);
	}
	
	public void setMouseListener(Input input) {
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
	}
	
}

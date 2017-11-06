package com.milner.platformEngine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.milner.platformEngine.graphix.Screen;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "Game";
	public static final int HEIGHT = 120;
	public static final int WIDTH = 160;
	private static final int SCALE = 3;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	private boolean running = false;
	public int gameTime = 0;
	private Screen screen;
	
	public int xOffset = 0, yOffset = 0;

	private InputHandler input = new InputHandler(this);

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void init() {
		try {
			screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/res/icons.png"))), input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long totalTime = System.currentTimeMillis();
		int fps = 5;
		init();
		render();
		while (running) {
			long currentTime = System.currentTimeMillis();
			long timePassed = currentTime - totalTime;
			if(timePassed >= 100/fps){
				render();
				tick();
				totalTime += timePassed;
				System.out.println("AD");
			}else if(timePassed >= 100/fps){
				tick();
				System.out.println("SD");
			}
		}
	}

	public void tick() {
		
		screen.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		screen.render();
		
		
		for (int y = 0; y < screen.h; y++) {
			for (int x = 0; x < screen.w; x++) {
				pixels[x + y * WIDTH] = screen.pixels[x + y * screen.w];
			}
		}
	

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0,0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		 game.start();
	}

}

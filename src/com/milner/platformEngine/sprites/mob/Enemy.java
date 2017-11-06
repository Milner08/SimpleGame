package com.milner.platformEngine.sprites.mob;

import java.awt.Color;
import java.util.Random;

import com.milner.platformEngine.Game;
import com.milner.platformEngine.InputHandler;
import com.milner.platformEngine.graphix.SpriteSheet;

public class Enemy extends Mob {
	private static int[][] tmptiles = {{36,37},{68,69}};
	private class Input implements Runnable{
		boolean running = true;
		public Key up = new Key();
		public Key down = new Key();
		public Key right = new Key();
		public Key left = new Key();
		
		private class Key{
			boolean down = false;	
			@SuppressWarnings("unused")
			public void changeState(){
				down = !down;
			}
		}
		public Input(){
			
		}
		@Override
		public void run() {
			Random r = new Random();
			long totalTime = System.currentTimeMillis();
			while (running) {
				int fps = r.nextInt(100);
				long currentTime = System.currentTimeMillis();
				long timePassed = currentTime - totalTime;
				totalTime += timePassed;
				if(timePassed >= fps*100){
					int tmp = (r.nextInt(10));
					if(tmp == 1){
						up.changeState();
					}else if(tmp == 2){
						down.changeState();
					}else if(tmp == 3){
						right.changeState();
					}else if(tmp == 4){
						left.changeState();
					}				
				}				
			}
		}
	}
	
	public Enemy(SpriteSheet sheet, int sw, int sh) {
		super(null, sheet, tmptiles, (Game.WIDTH/16), (Game.HEIGHT/16), 2,2, Game.WIDTH, Game.HEIGHT, sw, sh);
		setColours(0,new Color(0x5f5f5f).getRGB(),new Color(0xBBBBBB).getRGB(),new Color(0x555555).getRGB());
	}
	

}
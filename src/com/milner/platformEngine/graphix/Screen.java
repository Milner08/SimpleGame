package com.milner.platformEngine.graphix;

import com.milner.platformEngine.InputHandler;
import com.milner.platformEngine.level.Level;

public class Screen{
	private SpriteSheet sheet;
	public final int w;
	public final int h;
	public int[] pixels;
	public int[][] tiles;
	private Level level;
	boolean run1 = false;


	public Screen(int w, int h, SpriteSheet spriteSheet, InputHandler input) {
		this.sheet = spriteSheet;
		this.w = w;
		this.h = h;
		pixels = new int[w * h];
		level = new Level(w*3, h*3, sheet, input);
		tiles = new int[level.w][level.h];
		
	}

	public void render() {
		tiles = level.render();
		
		int[][] tmppixels = new int[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int xDist = x + level.player.xOffset;
				int yDist = y + level.player.yOffset;
				tmppixels[x][y] = tiles[xDist][yDist];
			}
		}
		
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				pixels[x + y * w] = tmppixels[x][y];
			}
		}

	}
	
	public void tick(){
		level.tick();
	}

}

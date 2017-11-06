package com.milner.platformEngine.graphix;

public class Tile {
	int row;
	int col;
	int w,h;
	SpriteSheet sheet;
	int[][] pixels2d;

	public Tile(SpriteSheet sheet, int col, int row){
		this.row = row;
		this.col = col;
		this.sheet = sheet;
		this.w = 8;
		this.h = 8;
		pixels2d = new int[w][h];
		getPixels(1);
	}

	private int[][] getPixels(int i){
		int startx = col*8;
		int starty = row*8;
		for(int y=0;y<h;y++){
			for(int x=0; x<w; x++){
				pixels2d[y][x] = sheet.pixels[(startx+x)*sheet.width+(starty+y)];
			}
		}
		return pixels2d;
	}
	
	public int[][] getPixels(){
		return pixels2d;
	}
}

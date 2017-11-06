package com.milner.platformEngine.graphix;

import java.awt.Color;
import java.util.ArrayList;

public class Sprite {
	public int w,h;
	public int x,y;
	protected SpriteSheet sheet;
	protected ArrayList<Tile> tiles = new ArrayList<Tile>();
	boolean passable = true;
	int angle = 0;
	public final int BLACK = Color.BLACK.getRGB(), 
		    		 DARK_GRAY = new Color(0x333333).getRGB(),
		    		 GRAY = new Color(0x999999).getRGB(),
		    		 WHITE = Color.WHITE.getRGB();	
	
	protected int colour1 = Color.BLACK.getRGB(), 
				  colour2 = new Color(75, 75,75).getRGB(),
				  colour3 = new Color(211, 211, 212).getRGB(),
				  colour4 = Color.WHITE.getRGB();	
	
	public Sprite(SpriteSheet sheet, int[][] tmptiles, int x, int y, int w, int h){
		this.sheet = sheet;
		for(int y1 = 0; y1 < tmptiles.length; y1++){
			int[] tmp = tmptiles[y1];
			for(int x1 = 0; x1 < tmp.length; x1++){
				if(tmp[x1] != 0){
					tiles.add(this.sheet.tiles.get(tmp[x1]));
				}
			}
		}
		this.w = w*8;
		this.h = h*8;
		this.x = x*8;
		this.y = y*8;
	}
	public Sprite(){
		
	}
	
	public void rotateCW(int angle){
		this.angle += angle;
	}
	
	public int[][] rotate(int[][] data){
		if(angle == 90){
			int[][] flipped = new int[h][w];
			for(int y=0;y<w;y++){
				for(int x=0; x<h; x++){
					flipped[w-y-1][x] = data[x][y];
				}
			}
			return flipped;
		}else if(angle == 180){
			int[][] flipped = new int[h][w];
			for(int y=0;y<w;y++){
				for(int x=0; x<h; x++){
					flipped[w-x-1][h-y-1] = data[x][y];
				}
			}
			return flipped;
		}else if(angle == 270){
			int[][] flipped = new int[w][h];
			for(int y=0;y<w;y++){
				for(int x=0; x<h; x++){
					flipped[w-y-1][x] = data[x][y];
				}
			}
			data = flipped;
			flipped = new int[w][h];
			for(int y=0;y<w;y++){
				for(int x=0; x<h; x++){
					flipped[w-x-1][h-y-1] = data[x][y];
				}
			}
			return flipped;
		}
		return data;
	}

	public void setColours(int col1, int col2, int col3, int col4){
		this.colour1 = col1;
		this.colour2 = col2;
		this.colour3 = col3;
		this.colour4 = col4;
	}

	public void setColours(int col1, int col2){
		this.colour1 = col1;
		this.colour2 = col2;
	}
	public void setColours(int col1, int col2, int col3){
		this.colour1 = col1;
		this.colour2 = col2;
		this.colour3 = col3;
	}
	
	public int[][] render(){
		int[][] pixels = new int[w][h];
		int[][] unrendered = new int[w][h];
		for(int w1 = 0; w1 < w/8; w1++){
			for( int h1 = 0; h1 < h/8; h1 ++){
				int[][] tmppixels = tiles.get(w1+h1*(w/8)).getPixels();
				for(int y1 = 0; y1 < 8; y1++){
					for(int x1 = 0; x1 < 8; x1++){
						unrendered[(w1*8)+y1][(h1*8)+x1] = tmppixels[y1][x1];
					}
				}
			}
		}
		
		unrendered = rotate(unrendered);
		
		for(int y=0;y<unrendered.length;y++){
			int[] tmplength = unrendered[y];
			for(int x=0; x<tmplength.length; x++){
				int tmp	= unrendered[x][y];
				if(tmp == GRAY){
					tmp = colour1;
				}else if(tmp == DARK_GRAY){
					tmp = colour2;
				}else if(tmp == BLACK){
					tmp = colour3;
				}else if(tmp == WHITE){
					tmp = colour4;
				}else{
					tmp = Color.BLUE.getRGB();
				}
				pixels[x][y]=tmp;
			}
		}
		
		return pixels;
	}
	
	public boolean isPassable(){
		return passable;
	}
	
	public void setPassable(boolean v){
		passable = v;
	}
	
}

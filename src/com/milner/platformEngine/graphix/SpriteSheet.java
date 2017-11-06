package com.milner.platformEngine.graphix;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
	public int width, height;
	public int[] pixels;
	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public SpriteSheet(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		int num = 0;
		for (int x = 0; x <width/8; x++){
			for(int y = 0; y <height/8; y++){
				num++;
				tiles.add(new Tile(this,x,y));
			}
		}
		System.out.println("Added "+num+" tiles");
	}
	
	public Tile getTile(int id){
		return tiles.get(id);
	}
}

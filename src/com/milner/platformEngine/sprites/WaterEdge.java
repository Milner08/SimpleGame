package com.milner.platformEngine.sprites;

import java.awt.Color;

import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;

public class WaterEdge extends Sprite{
	private static int[][] tmptiles = {{38}};

	public WaterEdge(SpriteSheet sheet, int x, int y){
		super(sheet, tmptiles, x,y,1,1);
		setColours(new Color(0x00000FF).getRGB(), Color.YELLOW.getRGB(), new Color(83,184,73).getRGB());
		setPassable(false);
	}
}

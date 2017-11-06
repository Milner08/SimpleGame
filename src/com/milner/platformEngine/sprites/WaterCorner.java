package com.milner.platformEngine.sprites;

import java.awt.Color;
import java.util.Random;

import com.milner.platformEngine.graphix.Sprite;
import com.milner.platformEngine.graphix.SpriteSheet;

public class WaterCorner extends Sprite{
	private static int[][] tmptiles = {{39}};

	public WaterCorner(SpriteSheet sheet, int x, int y){
		super(sheet, tmptiles, x,y,1,1);
		setColours(new Color(83,184,73).getRGB(), Color.YELLOW.getRGB(),new Color(0x00000FF).getRGB() );
		setPassable(false);
	}
}
